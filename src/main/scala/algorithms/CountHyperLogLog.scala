package algorithms

import breeze.linalg.max
import breeze.numerics.pow
import org.apache.spark.rdd.RDD
import twitter4j.{HashtagEntity, Status}

import scala.util.hashing.MurmurHash3

class CountHyperLogLog extends BigDataAlgorithm {

  var no_of_zeros = 0
  val seed = 42//Random.nextInt()

  override def calculate(rdd: RDD[Status]): Unit = {
    rdd.flatMap(status => status.getHashtagEntities).foreach(count)
  }

  private def count(hashTag: HashtagEntity): Unit = {
    var hash = MurmurHash3.stringHash(hashTag.getText, seed)
    var i = 0
    while ((hash & 1) == 0) {
      hash = hash >> 1
      i += 1
    }
    no_of_zeros = max(no_of_zeros, i)
  }

  override def getResults: String = {
    pow(2, no_of_zeros).toString
  }
}
