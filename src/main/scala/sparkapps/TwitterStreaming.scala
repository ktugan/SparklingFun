package sparkapps

import algorithms.BigDataAlgorithm
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object TwitterStreaming {
  def startTwitterStreamAlgorithm(algorithms: Seq[BigDataAlgorithm]) = {
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val consumerKey = TwitterCredentials.consumerKey
    val consumerSecret = TwitterCredentials.consumerSecret
    val accessToken = TwitterCredentials.accessToken
    val accessTokenSecret = TwitterCredentials.accessTokenSecret


    // Set the system properties so that Twitter4j library used by twitter stream
    // can use them to generat OAuth credentials
    System.setProperty("twitter4j.oauth.consumerKey", consumerKey)
    System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret)
    System.setProperty("twitter4j.oauth.accessToken", accessToken)
    System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret)

    val sparkConf = new SparkConf().setAppName("Sparkling Fun").setMaster("local[2]")
    val ssc = new StreamingContext(sparkConf, Seconds(2))
    val stream = TwitterUtils.createStream(ssc, None)

    stream.foreachRDD(rdd => {
      algorithms.foreach(algorithm => algorithm.calculate(rdd))
    })

    ssc.start()
    ssc.awaitTermination()
  }

}