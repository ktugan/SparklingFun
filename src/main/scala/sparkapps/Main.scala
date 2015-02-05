package sparkapps

import algorithms.SimpleCounter

object Main {

  def main(args: Array[String])= {
    val algorithm = new SimpleCounter()
    TwitterStreaming.startTwitterStreamAlgorithm(algorithm)
  }
}
