package Classes

case class posData(item: String, store: String, dateCode: java.sql.Timestamp, salesDollars: Double, salesUnits: Double ) extends Ordered [posData] {

  // Custom sort function that sorts Key fields for this class
  def compare(that: posData): Int =
    this.item compare that.item match {
      case 0 =>
        this.store compare that.store match {
          case 0 => this.dateCode.compareTo(that.dateCode)
          case c => c
        }
      case c => c
    }

}
