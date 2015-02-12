package sparkapps

import algorithms._

object Main {

  def main(args: Array[String])= {
    val algorithms = Array(
    //pure counting
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

    //TwitterStreaming.startTwitterStreamAlgorithm(algorithms)
  }
}
