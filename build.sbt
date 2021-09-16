ThisBuild / scalaVersion := "3.0.2"
val zioV     = "1.0.11"
val calibanV = "1.1.1"
val configV  = "1.0.6"
val postgres = "org.postgresql" % "postgresql"     % "42.2.8"
val quill    = "io.getquill"   %% "quill-jdbc-zio" % "3.7.2.Beta1.4"
val magnolia   = "com.softwaremill.magnolia1_3" %% "magnolia"   % "1.0.0-M5"
lazy val zjson = "dev.zio"                      %% "zio-json"   % "0.2.0-M1"
lazy val zhttp = "io.d11"                       %% "zhttp"      % "1.0.0.0-RC17"
val skunk      = "org.tpolecat"                 %% "skunk-core" % "0.2.0"
val scalQa = "org.scalqa" %% "scalqa" % "0.99"
lazy val zio = List(
  "dev.zio" %% "zio",
  "dev.zio" %% "zio-streams"
).map(_ % zioV)

lazy val caliban = List(
  "com.github.ghostdogpr" %% "caliban",
  "com.github.ghostdogpr" %% "caliban-zio-http"
).map(_ % calibanV)

lazy val zioTest = List(
  "dev.zio" %% "zio-test",
  "dev.zio" %% "zio-test-sbt",
  "dev.zio" %% "zio-test-magnolia"
).map(_ % zioV % Test)

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-simple",
    scalacOptions := List(
      "-feature",
      "-unchecked",
      "-deprecation",
      "-Ykind-projector",
      // "-Ysafe-init",
      "-Yexplicit-nulls",
      "-Xfatal-warnings"
    ) ++ List("-rewrite", "-indent") ++ List("-source", "future"),
    Compile / console / scalacOptions --= List(
      "-Wunused:_",
      "-Xfatal-warnings"
    ),
    Test / console / scalacOptions :=
      (Compile / console / scalacOptions).value,
    libraryDependencies ++=
      caliban ++ zio ++ zioTest // ++ config
        ++ List(
          postgres,
          skunk,
          magnolia,
          zjson,
          zhttp,
	  scalQa
        )
  ) //TODO switch to quill at some point
scalafmtOnCompile := true
publish / skip    := true
testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
releaseVersionBump            := sbtrelease.Version.Bump.Minor
Global / onChangedBuildSource := ReloadOnSourceChanges
