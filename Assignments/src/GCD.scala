

object GCD
{
  def main(args: Array[String]) {
    println(getGCD(45,12))
  }

  def getGCD(num1: Int,num2: Int): Int = {
    if(num2 ==0) num1 else getGCD(num2, num1%num2)
  }
}