package Classes

object DoctorServiceParser {

  def ParseDoctorService(line: String): Classes.DoctorService = {
    val fields = line.split(",")

    val uhid = fields(0)
    val locationid = fields(1)
    val doctorid = fields(2)
    val billdate = fields(3)
    val servicename = fields(4)
    val servicequantity = fields(5)
    val starttime = fields(6)
    val endtime = fields(7)
    val servicetype = fields(8)
    val servicecategory = fields(9)
    val deptname = fields(10)

    DoctorService(uhid,
      locationid,
      doctorid,
      billdate,
      servicename,
      servicequantity,
      starttime,
      endtime,
      servicetype,
      servicecategory,
      deptname)
  }
}
