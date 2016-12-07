import org.mongodb.scala.{MongoClient, MongoDatabase}
import Helpers._

object DelmeMain extends App {

  val MONGO_DATABASE = "skillen"

  // To directly connect to the default server localhost on port 27017
  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase(MONGO_DATABASE)

  println("BEGIN")

  val collection = database.getCollection("books")

  collection.find().first().printHeadResult()

  println("END")

}
