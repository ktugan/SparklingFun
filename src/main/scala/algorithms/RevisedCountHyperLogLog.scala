package algorithms

import breeze.linalg.max
import breeze.numerics.pow
import org.apache.spark.rdd.RDD
import twitter4j.{HashtagEntity, Status}
import utils.MathHelper

import scala.util.hashing.MurmurHash3

object RevisedCountHyperLogLog extends BigDataAlgorithm {

  val n = 5
  var no_of_zeros = Array.fill(n)(0)
  val seeds = Array.fill(n)(scala.util.Random.nextInt())


  override def calculate(rdd: RDD[Status]): Unit = {
    rdd.flatMap(status => status.getHashtagEntities).foreach(count)
  }

  private def count(hashTag: HashtagEntity): Unit = {

    for (c <- 0 to seeds.length - 1) {
      var hash = MurmurHash3.stringHash(hashTag.getText, seeds(c))
      var i = 0
      while ((hash & 1) == 0) {
        hash = hash >> 1
        i += 1
      }
      no_of_zeros(c) = max(no_of_zeros(c), i)
    }
  }

  override def print(): Unit = {
    val avg = MathHelper.harmonicMean(no_of_zeros.map(i => (pow(2, i) - 1)))
    println(avg.toInt)
  }
}
