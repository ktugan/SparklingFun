package ui

import java.lang.Boolean
import javafx.application.Platform
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.chart.XYChart.{Data, Series}
import javafx.scene.chart.{LineChart, XYChart}
import javafx.scene.control.CheckBox
import javafx.scene.layout.FlowPane

import algorithms._
import utils.AlgorithmConsolePrinter

import scala.concurrent.Future

class MainController() {
  @FXML val lineChart: LineChart[Long, Long] = null
  @FXML val algorithmsFlowPane: FlowPane = null;

  val lineChartData: ObservableList[XYChart.Series[Long, Long]] = FXCollections.observableArrayList[XYChart.Series[Long, Long]]
  val serieses = new java.util.HashMap[String, XYChart.Series[Long, Long]]()

  @FXML private def initialize() {
    initSpark()
    lineChart.setData(lineChartData)
  }


  def initSpark(): Unit = {
    val algorithms = Algorithms.load()
    //add callbacks

    //adds checkboxes and handles their selected event
    algorithms.foreach(algorithm => {
      val checkbox = new CheckBox(algorithm.name)
      checkbox.selectedProperty().addListener(new ChangeListener[Boolean] {
        override def changed(observableValue: ObservableValue[_ <: Boolean], t: Boolean, newvalue: Boolean): Unit = {
          if (newvalue)
            AlgorithmManager.add(algorithm)
          else
            AlgorithmManager.remove(algorithm)
        }
      })
      algorithmsFlowPane.getChildren.add(checkbox)
    })
  }

  /**
   * Starts the Spark Streaming Service
   */
  def startSpark(): Unit = {
    //populate hashmap and initialize algorithms
    AlgorithmManager.algorithms.foreach(algo => {
      val series = new Series[Long, Long]()
      series.setName(algo.name)
      serieses.put(algo.name, series)
      lineChartData.add(series)
    })
    lineChart.createSymbolsProperty


    AlgorithmManager.callbacks += FillChart
    AlgorithmManager.callbacks += AlgorithmConsolePrinter
    import scala.concurrent.ExecutionContext.Implicits.global
    Future {
      AlgorithmManager.initializeStreaming(AlgorithmManager.algorithms)
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
