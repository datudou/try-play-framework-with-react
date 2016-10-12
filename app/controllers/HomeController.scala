package controllers

import javax.inject._

import play.api._
import play.api.Play.current
import model.SunInfo
import play.api.libs.ws.WS
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action.async {
    val responseF = WS.url(http://api.sunrise-sunset.org/json?"+
    "lat=-33.8830&lng=151.2167&formatted=0").get()
    responseF.map { response =>
      val json = response.json
      val sunriseTimeStr = (json \ "results" \ "sunrise").as[String]
      val sunsetTimeStr = (json \ "results" \ "sunset").as[String]
      val sunInfo = SunInfo(sunriseTimeStr, sunsetTimeStr)
      Ok(views.html.index(sunInfo))
    }
  }
}
