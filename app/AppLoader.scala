/**
  * Created by qianyiwang on 13/10/2016.
  */

import actors.StatsActor
import actors.StatsActor.Ping
import akka.actor.Props
import controllers.{Assets, HomeController}
import play.api.ApplicationLoader.Context
import play.api._
import play.api.routing.Router
import router.Routes
import com.softwaremill.macwire._
import play.api.libs.ws.ahc.{AhcConfigBuilder, AhcWSComponents}
import services.{SunService, WeatherService}
import filters.StatsFilter
import play.api.mvc._
import scalikejdbc.config.DBs

import scala.concurrent.Future

class AppApplicationLoader extends ApplicationLoader{
  def load(context: Context) = {
    LoggerConfigurator(context.environment.classLoader).foreach {
      configuration => configuration.configure(context.environment)
    }
    (new BuiltInComponentsFromContext(context) with AppComponents).application

  }
}

trait AppComponents extends BuiltInComponents with AhcWSComponents {
  lazy val assets: Assets = wire[Assets]
  lazy val prefix: String = "/"
  lazy val router: Router = wire[Routes]
  lazy val applicationController = wire[HomeController]
  lazy val sunService = wire[SunService]
  lazy val weatherService = wire[WeatherService]

  lazy val statsFilter: Filter = wire[StatsFilter]

  override lazy val httpFilters = Seq(statsFilter)
  lazy val statsActor = actorSystem.actorOf(
    Props(wire[StatsActor]), StatsActor.name)

  applicationLifecycle.addStopHook{()=>
    Logger.info("The app is about to stop")
    DBs.closeAll()
    Future.successful(Unit)
  }

  val onStart = {
    Logger.info("The app is about to start")
    DBs.setupAll()
    statsActor ! Ping
  }




 }
