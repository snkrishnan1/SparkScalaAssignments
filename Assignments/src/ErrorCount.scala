import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
object LogAnalyzer {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setAppName("Server Log Analyzer").setMaster("local[2]"))
    val logFile = "C:\\Krishnan\\Working\\BigData\\Learnings\\Test\\Matt\\201-Assignments\\Assignments\\Data\\server_log"
    val lines = sc.textFile(logFile)
    //lines.foreach(println)

    val errors = lines.filter(_.startsWith("ERROR"))
    val messages = errors.map(_.split("\t")).map(r => r(1))
    //messages.foreach(println)

    val tot = lines.count
    val msql = messages.filter(_.contains("mysql")).count
    val php = messages.filter(_.contains("php")).count
    val rail = messages.filter(_.contains("RailsApp")).count
    /*
            Now this logic right now is printing the statistics on console,
            you could save the results in a file, or DB or even pass message
            to other system here
    */
    println("Total msgs: %s, MySQL errs: %s, PHP errs: %s, Rails: %s, DONE: %s".format(tot, msql, php, rail, (tot - (msql+php+rail))))

  }
}

/*
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object ErrorCount {
  def main(args: Array[String]): Unit={


    val sc = SparkSession
      .builder()
      .appName("Count Errors in the Log")
      .config("spark.master","local[*]")
      .getOrCreate()

    // READ PROPERTIES FILE INSTEAD OF HARD CODED VALUE
    val p = new Utility.PropertyConnection()
    val ServerLogPath =  p.propConnection.getProperty("Server_Log")

println(ServerLogPath)
    // READ THE FILE
    //---------------------------------------------------------------------------
    val rows = sc.sparkContext.textFile(ServerLogPath, 2).cache()

    val errors = rows.filter(_.startsWith("ERROR"))
    //println(errors)

    //val errors = lines.filter(_.startsWith("ERROR"))
    val messages = errors.map(_.split("\t")).map(r => r(1))
   messages.collect()


    val tot = errors.count
    val msql = messages.filter(_.contains("mysql")).count
    val php = messages.filter(_.contains("php")).count
    val rail = messages.filter(_.contains("RailsApp")).count

    println("Total msgs: %s, MySQL errs: %s, PHP errs: %s, Rails: %s, DONE: %s".format(tot, msql, php, rail, (tot - (msql+php+rail))))


  }

}
 */