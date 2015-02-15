package com.sparkling.fun

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class MainUI extends Application {
  @Override
  def start(primaryStage: Stage) {
    primaryStage.setTitle("Sparkling Fun")
    primaryStage.setScene(new Scene(FXMLLoader.load(getClass.getResource("/main.fxml"))))
    primaryStage.show()
  }

  def launch(args: Array[String]) {
    Application.launch(args: _*)
  }
}

object Sparkling {
  def main(args: Array[String]) {
    new MainUI launch args
  }
}
