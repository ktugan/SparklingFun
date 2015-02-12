package algorithms

import breeze.numerics._
import org.apache.spark.rdd.RDD
import twitter4j.Status
import utils.MathHelper

import scala.util.Random

object RevisedMorrisCounter extends BigDataAlgorithm {

  val n = 5
  var counter = Array.fill(n)(0)

  override def calculate(x: RDD[Status]): Unit = {
    x.foreach(probably_increment)
  }

  def probably_increment(x: Status): Unit = {
    for (a <- 0 to counter.length - 1) {
      //need edge case because generating random of 0 is an error
      if (counter(a) == 0) {
        counter(a) += 1
      }

      //increment with probability 1 / 2^i
      val r = Random.nextInt(pow(2, counter(a)))
      if (r == 0) {
        counter(a) += 1
      }
    }
  }

  override def getResults: String = {
    val data = counter.map(i => pow(2, i) - 1)
    val harmon_avg = MathHelper.harmonicMean(data)
    val ar_avg = MathHelper.arithmeticMean(data)
    harmon_avg.toInt.toString + ";" + ar_avg.toInt.toString
  }
}
