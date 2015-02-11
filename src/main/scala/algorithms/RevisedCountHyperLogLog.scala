package algorithms

import breeze.linalg.max
import breeze.numerics.pow
import org.apache.spark.rdd.RDD
import twitter4j.Status

import scala.util.hashing.MurmurHash3

object RevisedCountHyperLogLog extends BigDataAlgorithm {

  var no_of_zeros = Array(0, 0, 0, 0, 0)
  val seeds = Array(1, 2, 3, 4, 5)


  override def calculate(rdd: RDD[Status]): Unit = {
    rdd.foreach(count)
  }

  private def count(status: Status): Unit = {

    for (c <- 0 to seeds.length - 1) {
      var hash = MurmurHash3.stringHash(status.getUser.getId.toString, seeds(c))
      var i = 0
      while ((hash & 1) == 0) {
        hash = hash >> 1
        i += 1
      }
      no_of_zeros(c) = max(no_of_zeros(c), i)
    }
  }

  override def print(): Unit = {
    var avg = 0
    no_of_zeros.foreach(i => avg += (pow(2, i) - 1) / no_of_zeros.length)

    println("RevisedCountHyperLogLog:".padTo(20, ' ') + avg)
  }
}
