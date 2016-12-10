import akka.actor.ActorSystem
import akka.event.LoggingAdapter
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes.Redirection
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.Materializer
import com.typesafe.config.Config
import controllers.MenuController
import models.MenuModels.JustRequest
import org.mongodb.scala.{MongoClient, MongoDatabase}
import spray.json.JsonParser

import scala.concurrent.{ExecutionContextExecutor, Future}


trait Service extends Protocols {
  implicit val system: ActorSystem
  implicit def executor: ExecutionContextExecutor
  implicit val materializer: Materializer

  def config: Config
  val logger: LoggingAdapter

  val MONGO_DATABASE = "skillen"
  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase(MONGO_DATABASE)
  val menuController = new MenuController(database)

  //  lazy val projectPath = config.getString("project.path")

  val apiRoutes: Route = {
    logRequestResult("API") {
      get {
        path("api" / "texts") {
          complete {
            Future {
              menuController.listBooks().toFuture()
            }
          }
        } ~
        path("api" / "workbench") {
          complete {
            menuController.loadText()
          }
        }
      }
    }
  }

  val localPath = "/Users/baskakov/WebstormProjects/skillen-ui/"

  lazy val routes: Route = {
    apiRoutes ~
    pathPrefix("static") {
      encodeResponse {
        getFromDirectory(localPath)
      }
    } ~
    pathPrefix("delme") {
      encodeResponse {
        getFromBrowseableDirectory(localPath)
      }
    } ~
    // REST request from backbone
    pathPrefix("workbench") {
        (post & entity(as[JustRequest])) { x =>
          complete {
            logger.warning("DEBUG = " + x)
            //            data(x.title) = x
            JsonParser("""{"success": true}""")
          }
        }
      }
    }
}
