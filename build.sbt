name := "madomako"

version := "0.1"

scalaVersion := "2.13.5"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.7",
  "org.scalatest" %% "scalatest" % "3.2.7" % "test",
  "dev.zio" %% "zio" % "1.0.9",
  "dev.zio" %% "zio-streams" % "1.0.8",
  "dev.zio" %% "zio-test" % "1.0.8"
)