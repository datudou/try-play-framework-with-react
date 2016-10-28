package model

import play.api.libs.json.Json
/**
  * Created by qianyiwang on 26/10/2016.
  */
case class CombinedData(sunInfo: SunInfo, temperature: Double, request: Int)

object CombinedData {
  implicit val writes = Json.writes[CombinedData]
}

