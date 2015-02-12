package algorithms

import org.apache.spark.rdd.RDD
import twitter4j.{HashtagEntity, Status}

import scala.collection.mutable


class NaiveCountDistinct extends BigDataAlgorithm {
  var set = new mutable.HashSet[String]()

  override def calculate(rdd: RDD[Status]): Unit = {
    rdd.flatMap(status => status.getHashtagEntities).foreach(count)
  }

  private def count(hashTag: HashtagEntity): Unit = {
    set.add(hashTag.getText)
  }

  override def getResults: String = set.size.toString
}
