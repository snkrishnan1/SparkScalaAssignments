import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.sql.SparkSession

object CalculateMaximumSalaryWithCSV {
  def main(args: Array[String]) ={
    //val sc = new SparkContext(new SparkConf().setAppName("Maximum Salary").setMaster("local[2]"))
    val sc= SparkSession
      .builder()
      .appName("Max Salary")
      .config("spark.master", "local[*]")
      .getOrCreate()

   // val sc = SparkSession
   //   .builder()
   //   .appName("Doctor Registration")
   //   .config("spark.master", "local[*]")
   //   .getOrCreate()

    val empFile = "C:\\Krishnan\\Working\\BigData\\Learnings\\Test\\Matt\\201-Assignments\\Assignments\\Data\\Employees.csv"
    val rows = sc.sparkContext.textFile(empFile).mapPartitions(_.drop(1))

    val employees = rows.map(Classes.EmployeeParser.ParseEmployee)
    //employees.foreach(println)

    val employeesDF = sc.sqlContext.createDataFrame(employees)
    employeesDF.printSchema()
    employeesDF.createOrReplaceTempView("EMP")

    //To List all the records
    val employeesRec = sc.sqlContext.sql("Select * from EMP")
    employeesRec.show

    //val empMaxSalaryRec = sc.sqlContext.sql("Select Name, JOBTITLE, DEPARTMENT, EMPLOYEEANNUALSALARY, ESTIMATEDANNUALSALARYMINUSFURLOUGHS From EMP where EMPLOYEEANNUALSALARY = MAX(EMPLOYEEANNUALSALARY)")
    //empMaxSalary.show

    //To get the maximum salary
    val empMaxSalaryValue = sc.sqlContext.sql("Select Max(EMPLOYEEANNUALSALARY) From EMP")
    empMaxSalaryValue.show

    //val empRec = rows.map(lines => lines.split(",")).map(r => r(4))

    //empRec.foreach(println)
    //val maxSal = empRec.max()
    //println("Maximum salary" + maxSal)
  }
}
