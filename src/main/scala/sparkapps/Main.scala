package sparkapps

import algorithms.SimpleCounter

object Main {

  def main(args: Array[String])= {
    TwitterStreaming.startTwitterStreamAlgorithm(SimpleCounter)
  }
}
