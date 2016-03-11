organization := "com.gardner"

name := "music-library"

version := "0.1"

scalaVersion := "2.11.8"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

javacOptions ++= Seq("-Xlint:unchecked")

libraryDependencies ++= Seq(
  "com.github.rwl" % "jtransforms" % "2.4.0"
)

testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oD")

jacoco.settings

jacoco.reportFormats in jacoco.Config := Seq(
  de.johoop.jacoco4sbt.ScalaHTMLReport(encoding = "utf-8", withBranchCoverage = true))
