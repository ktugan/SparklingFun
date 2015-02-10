package sparkapps

import algorithms.{
TopNCountMinSketch,
RevisedMorrisCounter,
MorrisCounter,
SimpleCounter,
NaiveCountDistinct}

object Main {

  def main(args: Array[String])= {
    val algorithms = Array(
      SimpleCounter,
      NaiveCountDistinct,
      MorrisCounter,
      RevisedMorrisCounter,
      TopNCountMinSketch
    )

    TwitterStreaming.startTwitterStreamAlgorithm(algorithms)
  }
}
