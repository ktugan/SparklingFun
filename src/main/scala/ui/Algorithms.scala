package ui

import algorithms._

/**
 * @author Kadir Tugan
 */
object Algorithms {

  def load(): Seq[BigDataAlgorithm] ={
    List(
      SimpleCounter,
      MorrisCounter,
      RevisedMorrisCounter
    )
  }
}
