import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
object WordCountInFile {
  def main(args: Array[String]){

    val sc = new SparkContext(new SparkConf().setAppName("Count words in file").setMaster("local[2]"))

    val logFile = "C:\\Krishnan\\Working\\BigData\\Learnings\\Test\\Matt\\201-Assignments\\Assignments\\Data\\server_log"
    val lines = sc.textFile(logFile)
    val words = lines.map(_.split("\t"))
    val wordsCount = words.count

    println("Total words count in the file : " + wordsCount)
  }
}
