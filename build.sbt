organization := "com.gardner"

name := "music-library"

version := "0.1"

scalaVersion := "2.11.8"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

javacOptions ++= Seq("-Xlint:unchecked")

libraryDependencies ++= Seq(
  "com.github.rwl" % "jtransforms" % "2.4.0",
  "org.deeplearning4j" % "deeplearning4j-core" % "0.4-rc3.8",
  "org.deeplearning4j" % "deeplearning4j-nlp" % "0.4-rc3.8",
  "org.nd4j" % "nd4j-x86" % "0.4-rc3.8",
  "org.nd4j" % "canova-api" % "0.0.0.14",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test"
)

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oD")

jacoco.settings

jacoco.reportFormats in jacoco.Config := Seq(
  de.johoop.jacoco4sbt.ScalaHTMLReport(encoding = "utf-8", withBranchCoverage = true))
