name := "skillen-nlp"

version := "1.0"

scalaVersion := "2.12.1"

mainClass in Compile := Some("Main")

libraryDependencies ++= {
  val akkaV = "2.4.16"
  val akkaStreamV = "10.0.1"
  val luceneVersion = "6.3.0"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-stream" % akkaV,
    "com.typesafe.akka" %% "akka-http-core" % akkaStreamV,
    "com.typesafe.akka" %% "akka-http" % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaStreamV,

    "org.apache.lucene" % "lucene-core" % luceneVersion,
    "org.apache.lucene" % "lucene-analyzers-common" % luceneVersion,
    "org.apache.lucene" % "lucene-queryparser" % luceneVersion,

    "org.mongodb.scala" %% "mongo-scala-driver" % "1.2.1",

    "org.scalatest" %% "scalatest" % "3.0.1" % "test"
  )

}
