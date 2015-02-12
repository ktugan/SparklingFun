package algorithms

import org.apache.spark.rdd.RDD
import twitter4j.Status

abstract class BigDataAlgorithm extends Serializable{
  val name = this.getClass.getSimpleName

  AlgorithmManager.register(this)
  def calculate(x: RDD[Status])
  def getResults: String
}


