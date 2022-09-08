ThisBuild / organization := "us.logico-philosophic"
ThisBuild / description := "FastText language identification, for Java"
ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "3.0.0"
ThisBuild / resolvers ++= Seq(
  "Typesafe Repo" at "https://repo.typesafe.com/typesafe/releases/",
  "Secured Central Repository" at "https://repo1.maven.org/maven2")

ThisBuild / autoScalaLibrary := false
ThisBuild / crossPaths := false
ThisBuild / libraryDependencies ++= Seq(
  "com.github.vinhkhuc" % "jfasttext" % "0.4",
  "org.scala-lang" %% "scala3-library" % scalaVersion.value % Test,
  "com.lihaoyi" %% "utest" % "0.7.10" % Test)
ThisBuild / testFrameworks += new TestFramework("utest.runner.Framework")
ThisBuild / publishMavenStyle := true
ThisBuild / Test / publishArtifact := false
ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishTo := sonatypePublishToBundle.value
ThisBuild / pomExtra := (
  <url>https://github.com/adambaker/langid4j</url>
  <licenses>
    <license>
      <name>MIT</name>
      <url>http://opensource.org/licenses/MIT</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>https://github.com/adambaker/langid4j</url>
    <connection>scm:git:git@github.com:adambaker/langid4j.git</connection>
  </scm>)

val jni = project.settings(
  name := "langid4j_jni",
  Test / fork := true)

