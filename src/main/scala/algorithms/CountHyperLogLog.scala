package algorithms

import breeze.linalg.max
import breeze.numerics.pow
import org.apache.spark.rdd.RDD
import twitter4j.Status

import scala.util.hashing.MurmurHash3

object CountHyperLogLog extends BigDataAlgorithm {

  var no_of_zeros = 0

  override def calculate(rdd: RDD[Status]): Unit = {
    rdd.foreach(count)
  }

  private def count(status: Status): Unit = {
    var hash = MurmurHash3.stringHash(status.getUser.getId.toString, 42)
    var i = 0
    while ((hash & 1) == 0) {
      hash = hash >> 1
      i += 1
    }
    no_of_zeros = max(no_of_zeros, i)
  }

  override def print(): Unit = {
    println("CountHyperLogLog:".padTo(20, ' ') + pow(2, no_of_zeros))
  }
}
