name := "skillen"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= {
  val akkaV = "2.4.14"
  val akkaStreamV = "10.0.0"
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

    "org.mongodb.scala" %% "mongo-scala-driver" % "1.2.1"
  )

}
