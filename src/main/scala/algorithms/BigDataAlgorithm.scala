package algorithms

import org.apache.spark.rdd.RDD
import twitter4j.Status

abstract class BigDataAlgorithm extends Serializable{
  val id = Counter.getClassId
  val name = this.getClass.getSimpleName.dropRight(1) + f"($id)"

  Logger.register(this)
  def calculate(x: RDD[Status])
  def getResults: String
}
private object Counter {
  var i = 0
  def getClassId: Int ={
    i+=1
    i
  }
}


