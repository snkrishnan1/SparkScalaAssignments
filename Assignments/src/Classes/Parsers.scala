package Classes

import org.joda.time.format.DateTimeFormat

object Parsers {


    def ParseYahooStock(line:String): Classes.YahooStock = {
//println(line)
        val fields = line.split(",")
        val dateFormat = DateTimeFormat.forPattern("MM/dd/yyyy")
        // date: String, open: Float, high: Float, low: Float, close: Float, volume: Int, adjClose: Float) {
        //val Calendar_Dt: java.sql.Timestamp = new java.sql.Timestamp(dateFormat.parseDateTime(fields(0)).getMillis)
        val Calendar_Dt = fields(0)
        val open = fields(1).toFloat
        val high = fields(2).toFloat
        val low = fields(3).toFloat
        val close = fields(4).toFloat
        val volume = fields(5).toInt
        val adjClose = fields(6).toFloat

        Classes.YahooStock(Calendar_Dt, open, high, low, close, volume, adjClose)
      }
      //  val Calendar_Dt: java.sql.Timestamp = new java.sql.Timestamp(dateFormat.parseDateTime(fields(0)).getMillis)

      //Classes.YahooStock(Calendar_Dt, DayOfWeek, WeekNumber, WeekBegin, WeekEnd, MonthString, QuarterString, YearNumber)



}
