package algorithms

import com.twitter.algebird.{HLL, HyperLogLogMonoid, HyperLogLogAggregator, HyperLogLog}
import org.apache.spark.rdd.RDD
import twitter4j.Status

object AlgeHyperLogLog extends BigDataAlgorithm{
  override def calculate(x: RDD[Status]): Unit = {
    x.flatMap(status => status.getHashtagEntities).map(h => h.getText).foreach(count)
  }

  val hll = new HyperLogLogMonoid(12)
  var globalHll = hll.zero

  def count(hashtag: String): Unit ={
    val hash: HLL = hll(hashtag.getBytes)
    globalHll = hll.plus(globalHll, hash)
  }

  override def getResults: String = globalHll.approximateSize.estimate.toString
}
