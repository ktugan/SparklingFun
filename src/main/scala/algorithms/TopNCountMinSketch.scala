package algorithms

import com.twitter.algebird._
import com.twitter.algebird.CMSHasherImplicits._
import org.apache.spark.rdd.RDD
import twitter4j.Status

object TopNCountMinSketch extends BigDataAlgorithm {

  val DELTA = 1E-3
  val EPS = 0.01
  val SEED = 1
  val TOPK = 10
  val PERC = 0.01


  val cms: TopNCMSMonoid[Long] = {
    TopNCMS.monoid[Long](EPS, DELTA, SEED, TOPK)
  }

  var top_cms: TopCMS[Long] = cms.zero

  override def calculate(x: RDD[Status]): Unit = {
    val users = x.map(status => status.getUser.getId)
    val top_cms_2 = cms.create(users.collect())

    top_cms = cms.plus(top_cms, top_cms_2)
  }

  override def print(): Unit = {
    val counted = top_cms.totalCount
    val heavy = top_cms.heavyHitters.mkString("[", ",", "]")

    println(heavy)
  }
}
