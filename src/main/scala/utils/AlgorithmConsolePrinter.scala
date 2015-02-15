package utils

import algorithms.{AlgorithmResult, AlgorithmsResultEvent}


object AlgorithmConsolePrinter extends AlgorithmsResultEvent
{
  override def callback(results: Seq[AlgorithmResult]): Unit = {
    results.foreach(algorithm => {
      print(algorithm.name.padTo(30, ' '))
      println(algorithm.value)
    })
    println("".padTo(60, '-'))
  }
}
