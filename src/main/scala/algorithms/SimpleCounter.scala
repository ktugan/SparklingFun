package algorithms

import twitter4j.Status

class SimpleCounter extends BigDataAlgorithm
{
  var i = 0
  override def calculate(x: Status): Unit = {
    i += 1
    if(i % 1000 == 1)
    {
      println("currently: " + i + " " + x.getUser().getScreenName())
    }
  }
}
