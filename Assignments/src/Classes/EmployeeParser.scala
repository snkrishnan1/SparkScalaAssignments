package Classes

object EmployeeParser {
  def ParseEmployee(line: String): Classes.Employee = {

    val fields = line.split(",")
    val Name = fields(0)
    val JOBTITLE = fields(2)
    val DEPARTMENT = fields(3)
    val temp = fields(4)

    temp.replace(",","")
    // println("Sal after ,: " + temp)
    temp.replace("$","")
    //println("Sal after $: " + temp)
    val EMPLOYEEANNUALSALARY = temp.toDouble

    val ESTIMATEDANNUALSALARYMINUSFURLOUGHS = fields(5)

    //def salCleaner (salary: String): String ={
    //  salary.replace(",","")
    //  salary.replace("$","")
    //}

    Classes.Employee(Name, JOBTITLE, DEPARTMENT, EMPLOYEEANNUALSALARY, ESTIMATEDANNUALSALARYMINUSFURLOUGHS)
  }
}
