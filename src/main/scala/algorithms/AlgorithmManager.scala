package algorithms

import sparkapps.TwitterStreaming

import scala.collection.mutable.ListBuffer

object AlgorithmManager {

  /**
   * Printing every x seconds a status
   */
  var time_interval = 5

  import java.util.concurrent._

  val ex = new ScheduledThreadPoolExecutor(1)
  val task = new Runnable {
    def run() = startCallbacks()
  }
  val f = ex.scheduleAtFixedRate(task, time_interval, time_interval, TimeUnit.SECONDS)

  /**
   * For registering every algorithm
   */
  var algorithms: ListBuffer[BigDataAlgorithm] = ListBuffer()

  def add(algorithm: BigDataAlgorithm): Unit = {
    algorithms = algorithms += algorithm
  }

  def remove(algorithm: BigDataAlgorithm): Unit = {
    algorithms = algorithms -= algorithm
  }


  def shutdown(): Unit ={
    ex.shutdown()
  }

  val callbacks = collection.mutable.MutableList[AlgorithmsResultEvent]()
  var counter = 0
  def startCallbacks(): Unit = {
    try {
      counter += 1
      val results = algorithms.map(algorithm => {
        new AlgorithmResult(counter, algorithm.name, algorithm.getResults)
      })
      callbacks.foreach(_.call(results))
    }
    catch {
      case e => e.printStackTrace()
    }

  }


  def initializeStreaming(algorithms : Seq[BigDataAlgorithm]): Unit = {
    TwitterStreaming.startTwitterStreamAlgorithm(algorithms)
  }


}

abstract class AlgorithmsResultEvent {
  def callback(results: Seq[AlgorithmResult])

  def call(results: Seq[AlgorithmResult]): Unit = {
    callback(results)
  }
}

class AlgorithmResult(_sequenceId: Int, _algorithm: String, _value: String) {
  val sequenceId = _sequenceId
  val name = _algorithm
  val value = _value
}


