// -----------------------------------------------------------------------------
//  Project definitons
// -----------------------------------------------------------------------------
lazy val root = project
  .in(file("."))
  .aggregate(
    `serviteur-api`,
    `serviteur-server`,
  )

lazy val `serviteur-api` = project
  .in(file("serviteur-api"))
  .settings(
    clearOnWatch ++
    dottySettings ++
    coreDependencies ++
    coreTestDependencies ++
    Seq.empty
  )

lazy val `serviteur-server` = project
  .in(file("serviteur-server"))
  .settings(
    clearOnWatch ++
    dottySettings ++
    coreDependencies ++
    coreTestDependencies ++
    Seq.empty
  )
  .dependsOn(
    `serviteur-api`,
  )

// -----------------------------------------------------------------------------
//  Common settings
// -----------------------------------------------------------------------------
val dottyVersion =
  "0.24.0-bin-20200331-7a28eef-NIGHTLY"

val dottySettings = Seq(
  version := "0.1.0",
  scalaVersion := dottyVersion,
)

val coreDependencies = Seq(
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-effect" % "2.1.2",
  ).map(_.withDottyCompat(dottyVersion)),
)

val coreTestDependencies = Seq(
  libraryDependencies ++= Seq(
    "com.novocode" % "junit-interface" % "0.11" % "test",
  ),
)

val clearOnWatch = Seq(
  ThisBuild / watchTriggeredMessage := Watch.clearScreenOnTrigger,
  ThisBuild  / watchBeforeCommand := Watch.clearScreen,
)
