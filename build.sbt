name := """play-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  evolutions,
  "joda-time" % "joda-time" % "2.9.4",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "com.softwaremill.macwire" %% "macros" % "2.2.0" % "provided",
  "com.softwaremill.macwire" %% "util" % "2.2.0",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0" % "test",
  "org.mockito" % "mockito-core" % "2.0.45-beta",
  "org.postgresql" % "postgresql" % "9.4.1207.jre7",
  "org.scalikejdbc" %% "scalikejdbc" % "2.3.5",
  "org.scalikejdbc" %% "scalikejdbc-config" % "2.3.5",
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "de.svenkubiak" % "jBCrypt" % "0.4.1"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"


fork in run := true