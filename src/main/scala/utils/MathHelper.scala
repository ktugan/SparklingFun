package utils

object MathHelper {

  def harmonicMean(seq: Seq[Int]): Double ={
    return seq.length / seq.map(i => 1.0 / i).sum
  }

}
