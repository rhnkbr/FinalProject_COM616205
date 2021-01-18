package com.PBO;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/main_form.fxml"));
        primaryStage.setTitle("System Management Fakultas dan Jurusan");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
