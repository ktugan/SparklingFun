package ui

import algorithms._
import utils.AlgorithmConsolePrinter

object ConsoleMain {

  def main(args: Array[String]) :Unit = {

    //Just by using the name, initialize the singletons
    //pure counting
    val algorithms = Array(
      SimpleCounter,
      MorrisCounter,
      RevisedMorrisCounter,

      //distinct counting
      NaiveCountDistinct,
      CountHyperLogLog,
      RevisedCountHyperLogLog,
      AlgeHyperLogLog,
      BloomCounter,

      //max n counting
      TopNCountMinSketch
      )
    algorithms.foreach(algo => AlgorithmManager.add(algo))


    //start the streaming
    AlgorithmManager.callbacks += AlgorithmConsolePrinter
    AlgorithmManager.initializeStreaming(algorithms)

  }
}
