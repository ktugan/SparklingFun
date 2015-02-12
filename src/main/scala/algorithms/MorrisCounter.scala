package algorithms


import breeze.numerics.pow
import org.apache.spark.rdd.RDD
import twitter4j.Status

import scala.util.Random

object MorrisCounter extends BigDataAlgorithm {
  var i = 0

  override def calculate(x: RDD[Status]): Unit = {
    x.foreach(probably_increment)
  }

  def probably_increment(x: Status): Unit = {
    //need edge case because generating random of 0 is an error
    if (i == 0) {
      i += 1
      return
    }

    //increment with probability 1 / 2^i
    val r = Random.nextInt(pow(2, i))
    if (r == 0) {
      i += 1
    }
  }

  override def getResults: String = {
    val approx = pow(2, i) - 1
    approx + " (" + i + ")"
  }
}
