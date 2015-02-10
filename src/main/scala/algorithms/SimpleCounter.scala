package algorithms

import org.apache.spark.rdd.RDD
import twitter4j.Status

object SimpleCounter extends BigDataAlgorithm
{
  var i = 0
  override def calculate(rdd: RDD[Status]): Unit = {
    rdd.foreach(count)
  }

  private def count(status : Status): Unit = {
    i += 1
  }

  override def print(): Unit = {
    println(f"SimpleCounter:\t $i")
  }
}
