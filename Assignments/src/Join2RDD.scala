//The below codes to be executed in Scala command prompt

/*
    // Example for joining 2 Integer Array RDD's
    val sc = new SparkContext(new SparkConf().setAppName("Join 2 RDD").setMaster("local[*]"))

    //To Join to Integer array RDD's
    val x = sc.parallelize(1 to 9)
    val y = sc.parallelize(6 to 15)

    val z = x.union(y)
    //To show all the array items including the duplicate
    z.collect

    //To show all the array items with no duplicate items
    z.distinct.collect

    // Example for joining 2 Dataframes (Customer & Product)

    val sc1 = SparkSession
      .builder()
      .appName("Join 2 RDD")
      .config("spark.master","local[*]")
      .getOrCreate()

    case class Products(productId:String, category:String)
    case class Customer(customerId:String, productId:String, quantity:Int)
    val rdd1 = sc.parallelize(Seq(
         Products("product1", "category1"),
         Products("product2", "category2"),
         Products("product3", "category3"),
         Products("product4", "category4")
         ))
    val rdd2 = sc.parallelize(Seq(
         Customer("customer1", "product1", 5),
         Customer("customer1", "product2", 6),
         Customer("customer2", "product3", 2),
         Customer("customer2", "product4", 9)
         ))

    val dfOrder = rdd2.toDF("customerId", "productId", "quantity")
    val dfCust = rdd1.toDF("customerId", "productId")
    dfOrder.registerTempTable("OrderDF")
    dfCust.registerTempTable("CustDF")


    sc1.sqlContext.sql("Select c.customerId, c.productId, o.quantity from CustDF c inner join OrderDF o on c.productId = o.productId").show

*/