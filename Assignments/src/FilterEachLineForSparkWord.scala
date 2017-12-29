import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

object FilterEachLineForSparkWord {

  def main(args: Array[String]){
    val sc= new SparkContext(new SparkConf().setAppName("Filter each line").setMaster("local[*]"))
    val filePath = "C:\\Krishnan\\Working\\BigData\\Learnings\\Test\\Matt\\201-Assignments\\Assignments\\Data\\SparkKeyword.txt"
    val lines = sc.textFile(filePath)

    //lines.foreach(println)
    val rows = lines.filter(word => word.contains("Spark"))
    rows.foreach(println)

  }


}
