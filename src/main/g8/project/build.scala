import sbt._,Keys._

object build extends Build{

  val buildSettings = Defaults.defaultSettings ++ Seq(
    scalaVersion := "$scala_version$",
    resolvers ++= Seq(
      "https://oss.sonatype.org/content/repositories/releases",
      "https://oss.sonatype.org/content/repositories/snapshots"
    ).map{u => u at u},
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

