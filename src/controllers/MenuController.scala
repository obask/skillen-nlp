package controllers

import models.MenuModels.{BookInfo, JustRequest, UploadBook, WorkbenchInfo}
import org.mongodb.scala.{Completed, Document, MongoDatabase, Observable, bson}
import spray.json.{JsBoolean, JsObject, JsString, JsTrue, JsValue, JsonParser}

import scala.concurrent.Future

class MenuController(val db: MongoDatabase) {

  def listBooks(): Observable[BookInfo] = {
    db.getCollection("books")
      .find()
      .limit(10)
      .map(BookInfo.fromDocument)
  }

  def loadText(book: String): Future[WorkbenchInfo] = {
    db.getCollection("books")
      .find(Document("url" -> title2url(book)))
      .map(data => {
//        println("QWERTYUIOP: " + data.keys)
        WorkbenchInfo(
          caption = data getString "title",
          text = someBookRepresentation
      )}
      )
      .head()
  }

  def uploadBook(data: UploadBook): Future[JsObject] = {
    val document = Document(
      "title" -> data.inputValue,
      "text" -> data.textAreaValue,
      "url" -> title2url(data.inputValue)
    )
    //    TODO check mongo result
    db.getCollection("books")
      .insertOne(document)
      .map(completed =>
        JsObject(
          "success" -> JsTrue,
          "status" -> JsString(completed.toString),
          "id" -> JsString(data.inputValue)
        ))
      .head()
  }

  private def title2url(s: String): String = {
    s.dropWhile(!_.isLetterOrDigit)
      .reverse
      .dropWhile(!_.isLetterOrDigit)
      .reverse
      .map(c => if (c.isLetterOrDigit) c else '-')
      .replace("--", "-")
      .replace("--", "-")
      .replace("--", "-")
      .replace("--", "-")
      .replace("--", "-")
      .toLowerCase
  }

  private val someBookRepresentation: Seq[Either[String, JustRequest]] = Seq(
      JustRequest("zz1", "1", c = "false"),
      "dsadsa 12 1 ",
      JustRequest("zz1", "4", c = "false"),
      " dsadsa"
    ) map {
      case j: JustRequest => Right(j)
      case s: String => Left(s)
    }

}
