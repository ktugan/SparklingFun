package com.sparkling.fun

import javafx.application.Application
import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.{Stage, WindowEvent}

import algorithms.AlgorithmManager

class MainUI extends Application {
  @Override
  def start(primaryStage: Stage) {
    primaryStage.setTitle("Sparkling Fun")
    primaryStage.setScene(new Scene(FXMLLoader.load(getClass.getResource("/main.fxml"))))
    primaryStage.show()

    primaryStage.setOnCloseRequest(new EventHandler[WindowEvent] {
      override def handle(t: WindowEvent): Unit = AlgorithmManager.shutdown()
    });



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
