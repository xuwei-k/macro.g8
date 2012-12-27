import sbt._,Keys._

object build extends Build{

  val buildSettings = Defaults.defaultSettings ++ Seq(
    scalaVersion := "$scala_version$",
    resolvers ++= Seq(
      Opts.resolver.sonatypeReleases
    ),
    organization := "$organization$",
    version := "$version$",
    shellPrompt in ThisBuild := { state =>
      Project.extract(state).currentRef.project + "> "
    },
    scalacOptions ++= Seq("-deprecation"),
    initialCommands in console := {
      "$organization$".map{"import " + _ + "._"}.mkString("\n")
    },
    libraryDependencies ++= Seq(
    )
  )

  lazy val root = Project(
    "root",
    file(".")
  ).settings(
    buildSettings ++ Seq(
      publishArtifact := false,
      publish := {},
      publishLocal := {}
    ) : _*
  )aggregate($name$,$subproject_name$)

  lazy val $name$ = Project(
    "$name$",
    file("$name$"),
    settings = buildSettings ++ Seq(
      libraryDependencies ++= Seq(
      )
    )
  )dependsOn($subproject_name$)

  lazy val $subproject_name$ = Project(
    "$subproject_name$",
    file("$subproject_name$"),
    settings = buildSettings ++ Seq(
      libraryDependencies <++= scalaVersion{ v =>
        Seq(
          "org.scala-lang" % "scala-reflect" % v
        )
      }
    )
  )
}

