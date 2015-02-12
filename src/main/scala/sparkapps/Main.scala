package sparkapps

import algorithms._

object Main {

  def main(args: Array[String])= {
    val algorithms = Array(
    //pure counting
      new SimpleCounter,
      new MorrisCounter,
      new RevisedMorrisCounter,

    //distinct counting
      new NaiveCountDistinct,
      new CountHyperLogLog,
      new RevisedCountHyperLogLog,
      new AlgeHyperLogLog,
      new BloomCounter,

    //max n counting
      new TopNCountMinSketch
    )

    TwitterStreaming.startTwitterStreamAlgorithm(algorithms)
  }
}
