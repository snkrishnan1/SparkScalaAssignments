import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext._
import org.apache.spark.sql.{SQLContext, Row}
import com.crealytics.spark.excel
import org.apache.spark.mllib.stat.Statistics
//import org.apache.spark.sql.SparkSession

//import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.types.{IntegerType, FloatType, StringType, StructField, StructType}
import java.io.File

object EmpPerformanceAnalysis {
  def main (args: Array[String]){

    val sc = SparkSession
      .builder()
      .appName("")
      .config("spark.master","local[*]")
      .getOrCreate()

    val empFile = "C:\\Krishnan\\Working\\BigData\\Learnings\\Test\\Matt\\201-Assignments\\Assignments\\Data\\EmpDatasets.xlsx"

    //Defining the schema for the Dataset which is to be loaded from Excel dataset
    val empSchema = StructType(
      List(StructField("satisfaction_level", FloatType, nullable = true),
        StructField("last_evaluation", FloatType, nullable = true),
        StructField("number_project", IntegerType, nullable = true),
        StructField("average_monthly_hours", IntegerType, nullable = true),
        StructField("time_spend_company", IntegerType, nullable = true),
        StructField("work_accident", IntegerType, nullable = true),
        StructField("left", IntegerType, nullable = true),
        StructField("promotion_last_5years", IntegerType, nullable = true),
        StructField("department", StringType, nullable = true),
        StructField("salary", StringType, nullable = true))
    )

    val employeesDF = sc.sqlContext.read.schema(empSchema).format("com.crealytics.spark.excel")
      .option("sheetName", "Sheet1")
      .option("useHeader", "true")
      .option("treatEmptyValuesAsNulls", "false")
      .option("inferSchema", "false")
      .option("location", "C:\\Krishnan\\Working\\BigData\\Learnings\\Test\\Matt\\201-Assignments\\Assignments\\Data\\EmpDatasets.xlsx")
      .option("addColorColumns", "False")
      .load("C:\\Krishnan\\Working\\BigData\\Learnings\\Test\\Matt\\201-Assignments\\Assignments\\Data\\EmpDatasets.xlsx")

    /*
    val employeesDF = sc.sqlContext.read.schema(employeeSchema)
      .format("com.crealytics.spark.excel")
      .option("sheetName", "Sheet1")
      .option("useHeader", "true")
      .option("treatEmptyValuesAsNulls", "false")
      .option("inferSchema", "false")
      .option("location", empFile)
      .option("addColorColumns", "False")
      .load()

    val employeesDF = sc.sqlContext.read.schema(employeeSchema)
      .format("com.crealytics.spark.excel")
      .option("sheetName", "Sheet1")
      .option("useHeader", "true")
      .option("treatEmptyValuesAsNulls", "false")
      .option("inferSchema", "false")
      .option("location", empFile)
      .option("addColorColumns", "False")
      .load()
*/
    employeesDF.printSchema()
    employeesDF.createOrReplaceTempView("EMP")

    //To List all the records
    //val employeesRec = sc.sqlContext.sql("Select * from EMP")
    //employeesRec.show


    //What is the most important criteria for an employee to stick to an organization?
    //(Hint: Implement/use correlation matrix, Output will be in string format Ex: Salary/Experience/Department etc.)
    //val reason = sc.sqlContext.sql("select satisfaction_level,left")
    employeesDF.stat.corr("satisfaction_level","left")
    //println("Correlation between satisfaction_level and left");
    println("Correlation between satisfaction_level and left : " + employeesDF.stat.corr("satisfaction_level","left"))
    //correlationVal.toFloat();

    //At what experience level in a company employees are more susceptible to resign?
    val expLevel = sc.sqlContext.sql("Select time_spend_company, count(1) as resigned_employees_count from EMP where left = 1 group by time_spend_company order by resigned_employees_count")
    expLevel.show

    //What is the ideal number of projects for an employee?
    val projectCount = sc.sqlContext.sql("select round(avg(number_project)) as Number_Of_Projects,time_spend_company from EMP group by time_spend_company")
    projectCount.show;


    //How important is hike to employees
    //(Output will be in format of string: Ex: Salary less important, Salary very important, Experience important etc.)
    val salaryImportance = sc.sqlContext.sql("select cast(avg(satisfaction_level) as decimal(10,2)) as satisfaction_level,salary from Emp where salary is not null group by salary order by satisfaction_level")
    salaryImportance.show;


    //Which group (Department) has highest attrition (leaving the company) (Output as graph)
    val employeeLeftCount = sc.sqlContext.sql("select count(left) as employees_left_count, department from Emp where department is not null  group by department")
    employeeLeftCount.show();

  }
}
