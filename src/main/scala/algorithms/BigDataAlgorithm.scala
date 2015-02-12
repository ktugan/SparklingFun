package algorithms

import org.apache.spark.rdd.RDD
import twitter4j.Status

abstract class BigDataAlgorithm extends Serializable{

  Logger.register(this)
  def calculate(x: RDD[Status])
  def print()
}
