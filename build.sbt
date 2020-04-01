val dottyVersion = "0.24.0-bin-20200331-7a28eef-NIGHTLY"

lazy val root = project
  .in(file("."))
  .settings(
    name := "dotty-simple",
    version := "0.1.0",

    scalaVersion := dottyVersion,

    libraryDependencies ++= Seq(
      ("org.typelevel" %% "cats-effect" % "2.1.2").withDottyCompat(dottyVersion),
      "com.novocode" % "junit-interface" % "0.11" % "test",
    )
  )
