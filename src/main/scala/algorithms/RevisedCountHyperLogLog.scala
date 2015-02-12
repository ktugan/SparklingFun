package algorithms

import breeze.linalg.max
import breeze.numerics.pow
import org.apache.spark.rdd.RDD
import twitter4j.{HashtagEntity, Status}

import scala.util.hashing.MurmurHash3

object RevisedCountHyperLogLog extends BigDataAlgorithm {

  var no_of_zeros = Array(0, 0, 0, 0, 0)
  val seeds = Array(1, 2, 3, 4, 5)


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
    var avg = 0
    no_of_zeros.foreach(i => avg += (pow(2, i) - 1) / no_of_zeros.length)

//    avg = no_of_zeros.sortWith(_ < _)(no_of_zeros.length/2)

    println(avg)
  }
}
