package services


import model.SunInfo
import org.joda.time.{DateTime, DateTimeZone}
import org.joda.time.format.DateTimeFormat
import play.api.libs.ws

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.Play.current
import play.api.libs.ws.WSClient



/**
  * Created by qianyiwang on 12/10/2016.
  */
class SunService (wsClient: WSClient){
  def getSunInfo(lat: Double, lon: Double):Future[SunInfo] ={
    val responseF = wsClient.url("http://api.sunrise-sunset.org/json?"+
      "lat=-33.8830&lng=151.2167&formatted=0").get()
    responseF.map{ response =>
      val json = response.json
      val sunriseTimeStr = (json \ "results" \ "sunrise").as[String]
      val sunsetTimeStr = (json \ "results" \ "sunset").as[String]
      val sunriseTime = DateTime.parse(sunriseTimeStr)
      val sunsetTime = DateTime.parse(sunsetTimeStr)
      val formatter = DateTimeFormat.forPattern("HH:mm:ss").withZone(DateTimeZone.forID("Australia/Sydney"))
      val sunInfo = SunInfo(formatter.print(sunriseTime),formatter.print(sunsetTime))
      sunInfo
    }

  }

}
