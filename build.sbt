ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

val sparkVersion = "3.1.3"

lazy val root = (project in file("."))
  .settings(
    name := "spark_course_2"
  )

//libraryDependencies ++= Seq(
//  "org.apache.spark" % "spark-core_2.13" % sparkVersion,
//  "org.apache.spark" % "spark-sql" % sparkVersion
//)

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion
)