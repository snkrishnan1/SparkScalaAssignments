package Utility

import java.util.Calendar
import org.apache.log4j._

class Logger {

  def LogMessage (message: String) {

    val stime = Calendar.getInstance().getTimeInMillis

    val rootLogger = LogManager.getRootLogger()
    rootLogger.setLevel(Level.ALL)

    rootLogger.info("time: " + stime + " : " + message)

  }

}
