import org.mongodb.scala.{Document, MongoClient, MongoDatabase}
import DelmeMongoHelpers._

object DelmeMain extends App {

  val MONGO_DATABASE = "skillen"
  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase(MONGO_DATABASE)


  def main(): Unit = {
    println("BEGIN")

    val collection = database.getCollection("books")
    val results: Seq[Document] = collection.find().limit(10).results()
    val values = results.map(_.getString("title"))

    println(values)

    println("END")
  }
  main()

}
