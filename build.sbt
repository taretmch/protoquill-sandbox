val scala3Version = "3.1.1"

lazy val root = project
  .in(file("."))
  .settings(
    name         := "ProtoQuill Sandbox",
    version      := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "io.getquill"   %% "quill-jdbc"           % "4.0.0",
      "com.zaxxer"     % "HikariCP"             % "4.0.3",
      "mysql"          % "mysql-connector-java" % "8.0.26",
    )
  )
