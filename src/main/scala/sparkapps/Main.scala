package sparkapps

import algorithms.{
TopNCountMinSketch,
RevisedMorrisCounter,
MorrisCounter,
SimpleCounter,
NaiveCountDistinct,
CountHyperLogLog,
RevisedCountHyperLogLog
}

object Main {

  def main(args: Array[String])= {
    val algorithms = Array(
      SimpleCounter,
      NaiveCountDistinct,
      MorrisCounter,
      RevisedMorrisCounter,
      TopNCountMinSketch,
      CountHyperLogLog,
      RevisedCountHyperLogLog
    )

    TwitterStreaming.startTwitterStreamAlgorithm(algorithms)
  }
}
