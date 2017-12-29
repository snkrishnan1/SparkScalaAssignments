package Classes

object RegistrationParser {
  def ParseRegistration(line: String): Classes.Registration = {
    val fields = line.split(",")

    val uhid1 = fields(0)
    val locationid = fields(1)
    val registration_date = fields(2)
    val gender = fields(3)
    val dob = fields(4)
    val mobile = fields(5)
    val martialstatus = fields(6)
    val birthplace = fields(7)
    val religion = fields(8)
    val ethinicgroup = fields(9)
    val disability = fields(10)
    val education = fields(11)
    val mothertoungue = fields(12)
    val bloodgroup = fields(13)
    val address = fields(14)
    val city = fields(15)
    val state = fields(16)
    val pincode = fields(17)
    val country = fields(18)

    Classes.Registration( uhid1,
                         locationid,
                         registration_date,
                         gender,
                         dob,
                         mobile,
                         martialstatus,
                         birthplace,
                         religion,
                         ethinicgroup,
                         disability ,
                         education,
                         mothertoungue,
                         bloodgroup,
                         address,
                         city,
                         state,
                         pincode,
                         country)

  }
}
