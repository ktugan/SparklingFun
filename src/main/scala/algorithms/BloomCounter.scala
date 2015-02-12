package algorithms

import com.twitter.algebird.BloomFilterMonoid
import org.apache.spark.rdd.RDD
import twitter4j.Status


object BloomCounter extends BigDataAlgorithm {
  var counter = 0
  var filter = new BloomFilterMonoid(9, 9001, scala.util.Random.nextInt())
  var current = filter.zero

  override def calculate(rdd: RDD[Status]): Unit = {
    rdd.flatMap(status => status.getHashtagEntities).map(h => h.getText).foreach(count)
  }

  private def count(hashTag: String): Unit = {
    if (current.contains(hashTag).isTrue) {
      counter += 1
    } else {
      current = current + hashTag
    }
  }

  override def getResults: String = counter.toString
}
