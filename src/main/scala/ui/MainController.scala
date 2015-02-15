package ui

import javafx.application.Platform
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.chart.XYChart.{Data, Series}
import javafx.scene.chart.{LineChart, XYChart}
import javafx.scene.control.Button

import algorithms._
import utils.AlgorithmConsolePrinter

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class MainController() {
  @FXML private val startStream: Button = null
  @FXML private val lineChart: LineChart[Long, Long] = null
  val lineChartData: ObservableList[XYChart.Series[Long, Long]] = FXCollections.observableArrayList[XYChart.Series[Long, Long]]
  val serieses = new java.util.HashMap[String, XYChart.Series[Long, Long]]()


  @FXML private def initialize() {
    initSpark()
    lineChart.setData(lineChartData)
    //populate hashmap
    AlgorithmManager.algorithms.foreach(algo => {
      val series = new Series[Long, Long]()
      series.setName(algo.name)
      serieses.put(algo.name, series)
      lineChartData.add(series)

    })
    lineChart.createSymbolsProperty
  }



  def initSpark(): Unit ={
    val algorithms = Array(
      //pure counting
      SimpleCounter,
      MorrisCounter,
      RevisedMorrisCounter
    )

    //add callbacks
    AlgorithmManager.callbacks += FillChart
    AlgorithmManager.callbacks += AlgorithmConsolePrinter

    Future {
      AlgorithmManager.initializeStreaming()
    }
  }

  object FillChart extends AlgorithmsResultEvent {
    override def callback(results: Seq[AlgorithmResult]): Unit = {
      results.foreach(algorithm => {
        val series = serieses.get(algorithm.name)
        val data = new Data[Long, Long](algorithm.sequenceId, algorithm.value.toLong)
        //run in javafx thread
        Platform.runLater(new Runnable {
          override def run(): Unit = series.getData.add(data)
        })

      })

    }
  }


}
