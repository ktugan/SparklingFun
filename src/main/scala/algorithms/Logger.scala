package algorithms


object Logger {
  /**
   * Printing every x seconds a status
   */
  var time_interval = 5
  import java.util.concurrent._
  val ex = new ScheduledThreadPoolExecutor(1)
  val task = new Runnable {
    def run() = printAlgorithms()
  }
  val f = ex.scheduleAtFixedRate(task, time_interval, time_interval, TimeUnit.SECONDS)


  var algorithms : List[BigDataAlgorithm] = List()
  def register(algorithm : BigDataAlgorithm): Unit ={
    algorithms = algorithm :: algorithms
  }


  def printAlgorithms(): Unit ={
    algorithms.foreach(algorithm => {
      print(algorithm.getClass.getSimpleName.padTo(30, ' '))
      algorithm.print()
    })
    println("".padTo(60, '-'))
  }
}


