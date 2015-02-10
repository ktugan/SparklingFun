package sparkapps

import algorithms.{RevisedMorrisCounter, MorrisCounter, SimpleCounter}
import breeze.numerics._

object Main {

  def main(args: Array[String])= {
    //val algorithms = Array(SimpleCounter, MorrisCounter, RevisedMorrisCounter)
    //TwitterStreaming.startTwitterStreamAlgorithm(algorithms)


    for(i<-0 to 10000){
      RevisedMorrisCounter.probably_increment(null)
      MorrisCounter.probably_increment(null)
    }

    MorrisCounter.probably_increment(null)

  }
}
