val scala3Version = "3.1.3"

lazy val root = project
  .in(file("."))
  .settings(
    name         := "ProtoQuill Sandbox",
    version      := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "io.getquill"   %% "quill-jdbc" % "4.1.0",
      "org.scalameta" %% "munit"      % "0.7.29" % Test
    )
  )
