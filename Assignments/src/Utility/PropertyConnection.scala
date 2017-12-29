package Utility

import java.io.FileInputStream
import java.util.Properties

class PropertyConnection {

  def propConnection: Properties ={
    val prop = new Properties()
    prop.load(new FileInputStream("C:\\Krishnan\\Working\\BigData\\Learnings\\Test\\Matt\\SuperSimpleScalaSpark\\SuperSimple\\src\\resources\\Properties.info"))
    //prop.load(new FileInputStream("/home/mapradmin/m1021389/Properties.info"))
    //prop.load(new FileInputStream("/home/mapradmin/Properties1.info"))

    return prop

  }

}
