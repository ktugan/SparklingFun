package sparkapps

import algorithms.{BigDataAlgorithm, SimpleCounter}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object TwitterStreaming {
  def twittering(algorithm: BigDataAlgorithm) = {
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val consumerKey = "***REMOVED***"
    val consumerSecret = "***REMOVED***"
    val accessToken = "***REMOVED***"
    val accessTokenSecret = "***REMOVED***"


    // Set the system properties so that Twitter4j library used by twitter stream
    // can use them to generat OAuth credentials
    System.setProperty("twitter4j.oauth.consumerKey", consumerKey)
    System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret)
    System.setProperty("twitter4j.oauth.accessToken", accessToken)
    System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret)

    val sparkConf = new SparkConf().setAppName("TwitterPopularTags").setMaster("local[4]")
    val ssc = new StreamingContext(sparkConf, Seconds(2))
    val stream = TwitterUtils.createStream(ssc, None)

    stream.foreachRDD(rdd => {
      rdd.foreach(status => algorithm.calculate(status))
    })

    ssc.start()
    ssc.awaitTermination()
  }

  def main(args: Array[String])= {
    val algorithm = new SimpleCounter()
    twittering(algorithm)
  }

}