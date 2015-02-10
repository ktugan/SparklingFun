package sparkapps

import algorithms.{MorrisCounter, SimpleCounter}

object Main {

  def main(args: Array[String])= {
    val algorithms = Array(SimpleCounter, MorrisCounter)
    TwitterStreaming.startTwitterStreamAlgorithm(algorithms)
  }
}
