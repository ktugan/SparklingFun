package algorithms

import org.apache.spark.rdd.RDD
import twitter4j.Status

abstract class BigDataAlgorithm extends Serializable{

  def calculate(x: RDD[Status])
  def print()


  /**
   * Printing every x seconds a status
   */
  var time_interval = 5
  import java.util.concurrent._
  val ex = new ScheduledThreadPoolExecutor(1)
  val task = new Runnable {
    def run() = print()
  }
  val f = ex.scheduleAtFixedRate(task, time_interval, time_interval, TimeUnit.SECONDS)


}
