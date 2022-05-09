package com.example.demo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader mainInterfaceLoader = new FXMLLoader(main.class.getResource("main-interface.fxml"));
        Scene scene = new Scene(mainInterfaceLoader.load(), 400, 600);
        stage.setScene(scene);
        stage.setTitle("机械臂操作软件");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}