name := "Sparkling Fun"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.1.0"

libraryDependencies +=  "junit" % "junit" % "4.8.1" % "test"

libraryDependencies += "org.apache.spark" %% "spark-mllib" % "1.1.0"

libraryDependencies += "org.apache.spark" %% "spark-streaming" % "1.1.0"

libraryDependencies += "org.apache.spark" %% "spark-streaming-twitter" % "1.1.0"

libraryDependencies += "org.twitter4j" % "twitter4j-core" % "3.0.3"

libraryDependencies += "com.twitter" % "algebird-core_2.10" % "0.9.0"

resolvers += "Akka Repository" at "http://repo.akka.io/releases/"
