package ui

import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.FXML
import javafx.scene.chart.{LineChart, XYChart}
import javafx.scene.control.Button

import algorithms._
import sparkapps.TwitterStreaming

class MainController() {
  @FXML private val startStream: Button = null
  @FXML private val lineChart: LineChart[Double, Double] = null



  @FXML private def initialize() {
    //button.textProperty.bindBidirectional(stringProperty)

    val lineChartData: ObservableList[XYChart.Series[Double, Double]] = FXCollections.observableArrayList[XYChart.Series[Double, Double]]

    val series1: XYChart.Series[Double, Double] = new XYChart.Series[Double, Double]
    series1.setName("Series 1")
    series1.getData.add(new XYChart.Data[Double, Double](0.0, 1.0))
    series1.getData.add(new XYChart.Data[Double, Double](1.2, 1.4))
    series1.getData.add(new XYChart.Data[Double, Double](2.2, 1.9))
    series1.getData.add(new XYChart.Data[Double, Double](2.7, 2.3))
    series1.getData.add(new XYChart.Data[Double, Double](2.9, 0.5))
    lineChartData.add(series1)
    val series2: XYChart.Series[Double, Double] = new XYChart.Series[Double, Double]
    series2.setName("Series 2")
    series2.getData.add(new XYChart.Data[Double, Double](0.0, 1.6))
    series2.getData.add(new XYChart.Data[Double, Double](0.8, 0.4))
    series2.getData.add(new XYChart.Data[Double, Double](1.4, 2.9))
    series2.getData.add(new XYChart.Data[Double, Double](2.1, 1.3))
    series2.getData.add(new XYChart.Data[Double, Double](2.6, 0.9))
    lineChartData.add(series2)
    lineChart.setData(lineChartData)
    lineChart.createSymbolsProperty
  }


  def initSpark(): Unit ={
    val algorithms = Array(
      //pure counting
      SimpleCounter,
      MorrisCounter,
      RevisedMorrisCounter,

      //distinct counting
      NaiveCountDistinct,
      CountHyperLogLog,
      RevisedCountHyperLogLog,
      AlgeHyperLogLog,
      BloomCounter,

      //max n counting
      TopNCountMinSketch
    )

    TwitterStreaming.startTwitterStreamAlgorithm(algorithms)
  }


}
