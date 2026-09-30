ThisBuild / scalaVersion := "3.3.8"
ThisBuild / organization := "io.github.bjhartin"
ThisBuild / version := "0.1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(
    name := "stryker4s-for-cats",
    libraryDependencies ++= Seq(
      "io.stryker-mutator" %% "stryker4s-plugin-api" % "0.0.0-DEV",
      "org.typelevel" %% "cats-core" % "2.13.0",
      "org.typelevel" %% "cats-effect" % "3.7.1",
      "org.scalameta" %% "scalameta" % "4.17.4",
      "org.scalameta" %% "munit" % "1.3.6" % Test
    )
  )
