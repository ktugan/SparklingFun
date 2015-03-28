package com.sparkling.fun

import javafx.application.Application
import javafx.beans.value.{ObservableValue, ChangeListener}
import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.{WindowEvent, Stage}

import algorithms.AlgorithmManager

class MainUI extends Application {
  @Override
  def start(primaryStage: Stage) {
    primaryStage.setTitle("Sparkling Fun")
    primaryStage.setScene(new Scene(FXMLLoader.load(getClass.getResource("/main.fxml"))))
    primaryStage.show()

    //todo need to shutdown the other threads and executors too
    //shutdown algorithm manager when window closes
    primaryStage.onCloseRequestProperty.addListener(new ChangeListener[EventHandler[WindowEvent]] {
      override def changed(observableValue: ObservableValue[_ <: EventHandler[WindowEvent]], t: EventHandler[WindowEvent], t1: EventHandler[WindowEvent]): Unit = {
        AlgorithmManager.shutdown()
      }
    })


  }

  def launch(args: Array[String]) {
    Application.launch(args: _*)
  }
}

object MainUI {
  def main(args: Array[String]) {
    new MainUI launch args
  }
}
