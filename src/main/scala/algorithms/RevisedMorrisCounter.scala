package algorithms

import breeze.numerics._
import org.apache.spark.rdd.RDD
import twitter4j.Status

import scala.util.Random

object RevisedMorrisCounter extends BigDataAlgorithm
{

  var counter = Array(0,0,0,0,0)
  override def calculate(x: RDD[Status]): Unit = {
    x.foreach(probably_increment)
  }

  def probably_increment(x: Status): Unit ={
    for(a <- 0 to counter.length -1){
      //need edge case because generating random of 0 is an error
      if(counter(a) == 0){
        counter(a) += 1
      }

      //increment with probability 1 / 2^i
      val r =  Random.nextInt(pow(2, counter(a)))
      if(r == 0){
        counter(a) += 1
      }
    }
  }

  override def print(): Unit = {
    var avg = 0
    counter.foreach(i => avg += (pow(2, i) - 1) / counter.length)

    println(f"RevMorrisCounter:\t $avg")
  }
}
