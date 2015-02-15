package ui

import algorithms._
import utils.AlgorithmConsolePrinter

object ConsoleMain {

  def main(args: Array[String]) :Unit = {

    //Just by using the name, initialize the singletons
    //pure counting
    SimpleCounter
    MorrisCounter
    RevisedMorrisCounter

    //distinct counting
    NaiveCountDistinct
    CountHyperLogLog
    RevisedCountHyperLogLog
    AlgeHyperLogLog
    BloomCounter

    //max n counting
    TopNCountMinSketch

    //start the streaming
    AlgorithmManager.callbacks += AlgorithmConsolePrinter
    AlgorithmManager.initializeStreaming()

  }
}
