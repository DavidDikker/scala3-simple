val scala3Version = "3.0.1"
lazy val zioV = "1.0.9"
lazy val scalaTestV = "3.2.9"
val configV = "1.0.6"

lazy val zio = List(
  "dev.zio" %% "zio",
  "dev.zio" %% "zio-streams",
).map(_ % zioV)

lazy val config = List(
  "dev.zio" %% "zio-config",
  "dev.zio" %% "zio-config-typesafe",
  "dev.zio" %% "zio-config-typesafe").map(_ % configV)

lazy val zioTest = List(
  "dev.zio" %% "zio-test",
  "dev.zio" %% "zio-test-sbt",
  "dev.zio" %% "zio-test-magnolia"
).map(_ % zioV % Test)

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-simple",
    scalaVersion := scala3Version,
    libraryDependencies ++= zio ++ config ++ zioTest
  )
publish / skip := true
testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
releaseVersionBump := sbtrelease.Version.Bump.Major
