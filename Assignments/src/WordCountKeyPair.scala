import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

object WordCountKeyPair {
  def main(args: Array[String]): Unit ={

    /*   Count the occurence of a word in a sentence and create a pair - begin   */
    val sc = new SparkContext(new SparkConf().setAppName("Word count and create Pairs").setMaster("local[*]"))

    //Initialize the sentence
    val sentence = "this is a sentence this is the count this the actual sentence. we are going to count the occurences of the word this in this sentence"

    //Declare the word which need to be counted
    val wordToCount = "this"

    //Creating a pair by counting a word in a sentence
    val pairs = sentence.split(" ").filter(word => word == wordToCount).groupBy(_.toString).map(w=>(w._1,w._2.size))

    pairs.foreach(println)

    /*   Count the occurence of a word in a sentence and create a pair - end   */

    /*   Count the occurence of a word in a sentence by reading a file and create a pair - begin   */

    //val sc1 = new SparkContext(new SparkConf().setAppName("Filter each line").setMaster("local[*]"))
    val filePath = "C:\\Krishnan\\Working\\BigData\\Learnings\\Test\\Matt\\201-Assignments\\Assignments\\Data\\SparkKeyword.txt"
    val lines = sc.textFile(filePath)

    val wordToCountFromFile = "Spark"

    //Creating a pair by counting the occurence of  a word in each sentence in a file
    val pairsFromFile = lines.flatMap(line => line.split(" "))
      .filter(word => word == wordToCountFromFile)
      .map(w => (w,1))
      .reduceByKey(_+_)

    pairsFromFile.foreach(println)

    /*   Count the occurence of a word in a sentence by reading a file and create a pair - end   */

  }
}
