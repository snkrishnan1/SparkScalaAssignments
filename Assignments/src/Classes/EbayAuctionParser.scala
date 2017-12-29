package Classes

object EbayAuctionParser {
  def ParseEbayAuction(line: String): Classes.EbayAuction={

    val fields = line.split(",")

    val auctionid = fields(0)
    val bid= fields(1)
    val bidtime = fields(2)
    val bidder= fields(3)
    val bidderrate = fields(4)
    val openbid = fields(5)
    val price = fields(6)

    Classes.EbayAuction( auctionid, bid, bidtime, bidder, bidderrate, openbid, price)

  }

}
