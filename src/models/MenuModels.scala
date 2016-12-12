package models

import org.mongodb.scala.Document

object MenuModels {

  case class IpInfo(ip: String, country: Option[String], city: Option[String], latitude: Option[Double], longitude: Option[Double])

  case class IpPairSummary(distance: Option[Double], ip1Info: IpInfo, ip2Info: IpInfo)

  case class IpPairSummaryRequest(ip1: String, ip2: String)

  case class JustInfo(ip: String, country: Option[String])

  // token, word, class
  case class JustRequest(t: String, w: String, c: String)

  case class WorkbenchInfo(caption: String, text: Seq[Either[String, JustRequest]])

  case class BookInfo(title: String, url: String, text: String)

  object BookInfo {
    def fromDocument(d: Document): BookInfo = {
      BookInfo(d.getString("title"), d.getString("url"), d.getString("text"))
    }
  }

  case class UploadBook(inputValue: String, textAreaValue: Option[String])


}
