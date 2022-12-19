val commonSettings = Seq(
  scalaVersion     := "3.2.1",
  version          := "0.1.0",
  organization     := "com.github.taretmch",
  organizationName := "taretmch",
  scalacOptions ++= Seq(
    "-encoding",
    "UTF-8",
    "-feature",
    "-language:implicitConversions",
    "-unchecked",
    "-Ykind-projector"
  )
)

lazy val root = (project in file("."))
  .settings(name := "protoQuillSandbox")
  .settings(commonSettings: _*)
  .settings(libraryDependencies ++= Seq(
    "io.getquill"   %% "quill-jdbc"           % "4.6.0",
    "com.zaxxer"     % "HikariCP"             % "4.0.3",
    "mysql"          % "mysql-connector-java" % "8.0.26",
  ))
