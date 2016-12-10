import models.MenuModels._
import spray.json.DefaultJsonProtocol

trait Protocols extends DefaultJsonProtocol {
  implicit val justInfoFormat = jsonFormat2(JustInfo.apply)
  implicit val justRequestFormat = jsonFormat3(JustRequest.apply)
  implicit val justWorkbenchInfoFormat = jsonFormat2(WorkbenchInfo.apply)
  implicit val bookFormat = jsonFormat3(BookInfo.apply)
}
