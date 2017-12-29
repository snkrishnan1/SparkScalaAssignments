
//1. Please construct the dataset as given below using Seq and case class ActorDetail.
// 2. Construct a data frame using the above dataset by creating 2 partitions (use parallelize method).
/*
case class  ActorDetail(id: Int, actorname: String,genre: String,noofmovies: Int,hits: Int)

val actors = sc.parallelize(Seq(
    ActorDetail(1001,"Johnny Lever","Commedian",210,190),
    ActorDetail(1002,"Amit Mahesh","New Comer",2,1),
    ActorDetail(1003,"Salman Khan","SuperStar",300,291),
    ActorDetail(1004,"Johnny Depp","SuperStar",289,270),
    ActorDetail(1005,"Mallika Sherawat","Actress",20,10),
    ActorDetail(1006,"Amitabh Bachan","SuperStar",350,300),
    ActorDetail(1007,"Micheal Bijlu","New Comer",4,1),
    ActorDetail(1008,"Rocky Angelo","New Comer",3,1),
    ActorDetail(1009,"Rajani Kanth","SuperStar",400,399),
    ActorDetail(1010,"Arnold","SuperStar",261,242)
), 2)

val actorsDF = actors.toDF("id","actorname","genre","noofmovies","hits")
actorsDF.registerTempTable("ActorsDB")

//Show the all the details of the actDetailsDF and also show only the first two records of the actorDetails dataset
sqlContext.sql("Select * From ActorsDB").show

//To show first 2 records
sqlContext.sql("Select * From ActorsDB").take(2)

//How do you print the schema of the actor details dataframe?
actorsDF.printSchema

//Register the DataFrame as temp table and only select the rows for whom the no of hits are more than 250 and print the output.
sqlContext.sql("Select * from ActorsDB where hits>250").show


*/