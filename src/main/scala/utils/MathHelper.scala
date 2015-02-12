package utils

object MathHelper {

  def harmonicMean(seq: Seq[Int]): Double = seq.length / seq.map(i => 1.0 / i).sum

  def arithmeticMean(seq: Seq[Int]): Double = seq.map(i => i / seq.length.toDouble).sum
}
