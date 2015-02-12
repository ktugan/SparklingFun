package algorithms

import java.io.{File, FileWriter, BufferedWriter}


object AlgorithmManager {
  val writeToCsvFile = true //If you want the results in a csv

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

  /**
   * For registering every algorithm
   */
  var algorithms : List[BigDataAlgorithm] = List()
  def register(algorithm : BigDataAlgorithm): Unit ={
    algorithms =  algorithms ::: List(algorithm)
  }


  def printAlgorithms(): Unit ={
    if(writeToCsvFile){
      writeToCsv()
    }

    algorithms.foreach(algorithm => {
      print(algorithm.name.padTo(30, ' '))
      println(algorithm.getResults)
    })
    println("".padTo(60, '-'))
  }

  var writer : BufferedWriter = null


  def writeToCsv(): Unit={
    if(writeToCsvFile){
      writer = new BufferedWriter(new FileWriter(new File("output.csv")))
      writer.write(algorithms.map(_.name).mkString(";") + ";")
      writer.newLine()
    }

    algorithms.foreach(algo => {
      writer.write(algo.getResults+ ";")
    });
    writer.newLine()
    writer.flush()
  }


}


