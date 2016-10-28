package model

import play.api.libs.json.Json

case class SunInfo(sunrise: String, sunset: String)

object SunInfo {
  implicit val writes = Json.writes[SunInfo]
}
