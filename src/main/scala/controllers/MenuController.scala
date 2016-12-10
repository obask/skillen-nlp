package controllers

import models.MenuModels.{BookInfo, JustRequest, WorkbenchInfo}
import org.mongodb.scala.{Document, MongoDatabase, Observable, bson}

class MenuController(val db: MongoDatabase) {

  def listBooks(): Observable[BookInfo] = {
    db.getCollection("books")
      .find()
      .limit(10)
      .map(BookInfo.fromDocument)
  }

  def loadText() = WorkbenchInfo(
      caption = "dsadsada",
      text = data
  )

  private val data: Seq[Either[String, JustRequest]] = Seq(
      JustRequest("zz1", "1", c = "false"),
      "dsadsa 12 1 ",
      JustRequest("zz1", "4", c = "false"),
      " dsadsa"
    ) map {
      case j: JustRequest => Right(j)
      case s: String => Left(s)
    }

}
