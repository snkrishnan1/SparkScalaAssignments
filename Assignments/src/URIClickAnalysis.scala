
//1. Using Kafka, simulate streaming of live URI information from the dataset. Read from event.csv & stream the information

val spark = SparkSession.builder.appName("clickanalysis").master("local").enableHiveSupport().getOrCreate()

val streaming_context = new StreamingContext(spark.sparkContext, Seconds(5))

val kafkaParameters = Map[String, Object](
  "bootstrap.servers" -> args(0),
  "key.deserializer" -> classOf[StringDeserializer],
  "value.deserializer" -> classOf[StringDeserializer],
  "group.id" -> args(1),
  "auto.offset.reset" -> "earliest"
)

//Create a topic
val topicName = Array("topicURIClick")

//define the columns which are available in the events.csv and streamed
val fields = "display_id,uuid,document_id,timestamp,platform,geo_location"

val structfield = fields.split(",").map(fieldName => StructField(fieldName, StringType, nullable = true))
val sch = StructType(structfield)


def createArray(attrs: Array[String]):Array[String] ={
  var m: Array[String] = new Array[String](6)
  if(attrs.length == 6) {a = attrs} else {
    var i : Int = 0
    attrs.foreach(x => {
      m(i) = x
      i = i+1
    })
    m(5) = null
  }
  return m
}

val kafkaStreamData=  KafkaUtils.createDirectStream[String, String]( streaming_context, LocationStrategies.PreferConsistent, ConsumerStrategies.Subscribe[String, String]( topicName, kafkaParameters))

val strm_data  = kafkaStreamData.map(record => record.value())
stream.foreachRDD(record => {
  val rdd : RDD[Array[String]] = record.map(_.split(",")).map(attrs => createArray (attrs) )
  val rowRDD : RDD[Row] = rdd.map(attributes => Row(attributes(0), attributes(1), attributes(2), attributes(3), attributes(4), attributes(5)))
})

//2. Consume the streamed information from Kafka in Spark streaming. And, show live stream analysis for:
//a. Top 5 processed data over last 10 minutes, 30 minutes or 2 hours
//  b. Top 5 processed data over last 10 minutes, 30 minutes or 2 hours based on location (Hint: Use event.csv dataset and put the logic on display_id, uuid, document_id, timestamp, geo_location. For calculating the time, use timestamp (ms since 1970-01-01 - 1465876799998))

val uri_dt = rowRDD.toDF()

def getDay = udf((value: Int) => (value/86400000)+1)
def getWeek = udf((value: Int) => (value/604800000)+1)
def getMonth = udf((value: Int) => (value/60000)+1)

val dt_Df_uri=df.withColumn("day", getDay (uri_dt ("timestamp"))).withColumn("week", getWeek (uri_dt ("timestamp"))).withColumn("minutes", getMonth (uri_dt ("timestamp")))
dt_Df_uri.write.mode(SaveMode.Append).saveAsTable("data_uri")
dt_Df_uri.createOrReplaceTempView("uri_db")

//Top 5 processed data over last 10 mins: As in , from “ End time – 10 “ will provide //the last ten mins data
val uri_dt_10Min =spark.sql("select * from uri_db where minutes between 1 and 10  order by minutes desc")
uri_dt_10Min.show(5)

//3. Persist the obtained data from Kafka to Hive using SparkSQL. All data received goes to Hive
dt_Df_uri.write.mode(SaveMode.Append).saveAsTable("data_uri")


//  4. Using Spark, analyze the persistent data for daily, weekly & monthly duration to find top trending product (Use SparkSQL for solving this use case)
val demand = spark.sql("select document_id, day, count(*) as dfactcount from uri_db Groupby day, document_id")
demand.createOrReplaceTempView("demandfactor")

val max_demand = spark.sql("Select max(demandFactor) as mdem ,day from uri_db Group by day")
max_demand.createOrReplaceTempView("maxdemfact")

val result = spark.sql("SELECT A.day , " +
  "B.mdem , " +
  "A.document_ID " +
  "from demandfactor A, maxdemfact B " +
  "WHERE A.demand_factor = B.mdem " +
  "ORDER BY day ")

result.show(10)

val demand_m = spark.sql("select document_id, month, count(*) as dfactcount from uri_db Groupby month, document_id")
demand.createOrReplaceTempView("demandfactor_m")

val max_demand_m = spark.sql("Select max(demandFactor) as mdem ,month from uri_db Group by dmonth")
max_demand.createOrReplaceTempView("maxdemfact_m")

val result_month = spark.sql("SELECT A.month , " +
  "B.mdem , " +
  "A.document_ID " +
  "from demandfactor_m A, maxdemfact_m B " +
  "WHERE A.demand_factor = B.mdem " +
  "ORDER BY month ")

result_month.show(10)

//5. Find top 5 regions which have high demands. (This problem should be solved with HDFS, RDD’s and SparkSQL)
val topRegions = spark.sql("SELECT A.geo_location from demandfactor_m A ORDER BY A.mdem ")
topRegions.show(5)

