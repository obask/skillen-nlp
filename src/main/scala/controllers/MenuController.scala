package controllers

import models.MenuModels.BookInfo
import org.mongodb.scala.{Document, MongoDatabase, Observable, bson}

class MenuController(val db: MongoDatabase) {

  def listBooks(): Observable[BookInfo] = {
    db.getCollection("books")
      .find()
      .limit(10)
      .map(BookInfo.fromDocument)
  }

}
