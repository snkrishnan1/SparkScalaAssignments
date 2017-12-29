import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext._
import org.apache.spark.sql.{SQLContext, Row}
import com.databricks.spark.xml
import org.apache.spark.sql.SparkSession

object CalculateMaxSalaryFromXML {

  def main(args: Array[String]){
    //val sc = new SparkContext(new SparkConf().setAppName("Employees").setMaster("local[*]"))
  val sc = SparkSession
      .builder()
      .appName("")
      .config("spark.master","local[*]")
      .getOrCreate()

    val empFile = "C:\\Krishnan\\Working\\BigData\\Learnings\\Test\\Matt\\201-Assignments\\Assignments\\Data\\employees.xml"

    //val sqlContext = new SQLContext(sc)
    val employeesDF = sc.sqlContext.read
      .format("com.databricks.spark.xml")
      .option("rowTag", "employee")
      .load(empFile)

    employeesDF.printSchema()
    employeesDF.createOrReplaceTempView("EMP")

    //To List all the records
    val employeesRec = sc.sqlContext.sql("Select * from EMP")
    employeesRec.show

    //To get the maximum salary
    val empMaxSalaryValue = sc.sqlContext.sql("Select Max(salary) From EMP")
    empMaxSalaryValue.show

  }

}
