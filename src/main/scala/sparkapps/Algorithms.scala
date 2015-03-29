package sparkapps

import algorithms._

/**
 * @author Kadir Tugan
 */
object Algorithms {

  /**
   * Defines what algorithms should be loaded and returned
   * @return all algorithms to be loaded into the app
   */
  def load(): Seq[BigDataAlgorithm] ={
    List(
      SimpleCounter,
      MorrisCounter,
      RevisedMorrisCounter,

      //distinct counting
      NaiveCountDistinct,
      CountHyperLogLog,
      RevisedCountHyperLogLog,
      AlgeHyperLogLog,
      BloomCounter
    )
  }
}
