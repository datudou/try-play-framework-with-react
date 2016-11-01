package controllers

import java.util.concurrent.TimeUnit
import javax.inject._

import actors.StatsActor
import akka.actor.ActorSystem
import akka.util.Timeout
import akka.pattern.ask
import model.CombinedData
import play.api._
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import services.{SunService, WeatherService}


//import java.util.concurrent.TimeUndit
//
//import actors.StatsActor
//import akka.actor.ActorSystem
//import akka.util.Timeout
//import model.{CombinedData, SunInfo}
//import org.joda.time.{DateTimeZone, DateTime}
//import org.joda.time.format.DateTimeFormat
//import play.api.libs.json.Json
//import play.api.libs.ws.WS
//import play.api.mvc._
//
//
//import play.api.Play.current
//import services.{UserAuthAction, AuthService, WeatherService, SunService}
//import scala.concurrent.ExecutionContext.Implicits.global

import play.api.data.Form
import play.api.data.Forms._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() (weatherService: WeatherService, sunService: SunService, actorSystem: ActorSystem) extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index())
  }


  def data = Action.async {
    val lat = 32.06
    val lon = 118.78

    val sunInfoF = sunService.getSunInfo(lat, lon)
    val temperatureF = weatherService.getTemperature(lat, lon)
    implicit val timeout = Timeout(5, TimeUnit.SECONDS)
    val requestsF = (actorSystem.actorSelection(StatsActor.path) ?
      StatsActor.GetStats).mapTo[Int]
    for {
      sunInfo <- sunInfoF
      temperature <- temperatureF
      requests <- requestsF
    } yield {
      Ok(Json.toJson(CombinedData(sunInfo, temperature ,requests)))
    }
  }
}
