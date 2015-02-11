package sparkapps

import algorithms.{
TopNCountMinSketch,
RevisedMorrisCounter,
MorrisCounter,
SimpleCounter,
NaiveCountDistinct,
CountHyperLogLog
}

object Main {

  def main(args: Array[String])= {
    val algorithms = Array(
      SimpleCounter,
      NaiveCountDistinct,
      MorrisCounter,
      RevisedMorrisCounter,
      TopNCountMinSketch,
      CountHyperLogLog
    )

    TwitterStreaming.startTwitterStreamAlgorithm(algorithms)
  }
}
