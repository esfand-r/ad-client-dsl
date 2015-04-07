name := "AppDirect Client"

version := "0.1.0"

scalaVersion := "2.11.7"

organization := "com.appdirect"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

libraryDependencies ++= {
  Seq(
    "com.ning" % "async-http-client" % "1.9.31",
    "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.6.1"
  )
}
