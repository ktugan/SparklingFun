package algorithms

import algorithms.BigDataAlgorithm
import twitter4j.Status

class SimpleCounter extends BigDataAlgorithm
{
  var i = 0
  override def calculate(x: Status): Unit = {
    i += 1
    if(i % 1000 == 0)
    {
      println("currently: " + i)
    }
  }
}
