package services

import play.api.libs.ws.WSClient

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.Play.current


/**
  * Created by qianyiwang on 12/10/2016.
  */
class WeatherService(wsClient: WSClient) {
  def getTemperature(lat: Double, lon:Double): Future[Double] = {
    val weatherResponseF = wsClient.url("http://api.openweathermap.org/data/2.5/"+
      "weather?lat=-33.883&lon=151.2167&units=metric&APPID=78f547b12bc09028ee3832514b8b89cc").get()
    weatherResponseF.map { response =>
      val weatherJson = response.json
      val temperature = (weatherJson \ "main" \ "temp").as[Double]
      temperature
    }

  }

}
