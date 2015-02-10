package sparkapps

import algorithms.{TopNCountMinSketch, RevisedMorrisCounter, MorrisCounter, SimpleCounter}

object Main {

  def main(args: Array[String])= {
    val algorithms = Array(
      SimpleCounter,
      MorrisCounter,
      RevisedMorrisCounter,
      TopNCountMinSketch)

    TwitterStreaming.startTwitterStreamAlgorithm(algorithms)
  }
}
