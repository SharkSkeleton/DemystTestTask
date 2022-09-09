ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "first-step"
  )

val ZHTTPVersion = "2.0.0-RC7"

libraryDependencies ++= Seq(
  "io.d11" %% "zhttp" % ZHTTPVersion,
  "io.d11" %% "zhttp-test" % ZHTTPVersion % Test
)

libraryDependencies += "net.jcazevedo" %% "moultingyaml" % "0.4.2"

val circeVersion = "0.14.1"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.13"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.13" % "test"