package utils

import java.io.{FileWriter, File, BufferedWriter}

import algorithms.{AlgorithmResult, AlgorithmsResultEvent}

object AlgorithmCsvWriter extends AlgorithmsResultEvent {

  var writer: BufferedWriter = null
  override def callback(results: Seq[AlgorithmResult]): Unit = {
    val file = new File("output.csv")
    val fileExists = file.exists()
    writer = new BufferedWriter(new FileWriter(file, true))
    if (!fileExists)
      writer.write(results.map(_.name).mkString(";"))
    writer.newLine()

    writer.write(results.map(_.value).mkString(";"))
    writer.close()
  }

}
