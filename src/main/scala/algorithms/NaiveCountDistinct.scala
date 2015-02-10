package algorithms

import org.apache.spark.rdd.RDD
import twitter4j.Status

import scala.collection.mutable


object NaiveCountDistinct extends BigDataAlgorithm {
  var set = new mutable.HashSet[Long]()

  override def calculate(rdd: RDD[Status]): Unit = {
    rdd.foreach(count)
  }

  private def count(status: Status): Unit = {
    set.add(status.getUser.getId)
  }

  override def print(): Unit = {
    println("NaiveCountDistinct:".padTo(20, ' ') + set.size)
  }
}
