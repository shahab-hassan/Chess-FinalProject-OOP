package com.example.finalproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class StyledProgressBar extends Application {

    @Override
    public void start(Stage primaryStage) throws MalformedURLException {
        ProgressBar progressBar = new ProgressBar();
        progressBar.getStyleClass().add("progress-bar");

        StackPane root = new StackPane(progressBar);

        Scene scene = new Scene(root, 300, 200);
        URL urlCss = new File("src/application.css").toURI().toURL();
        scene.getStylesheets().add(urlCss.toString());

        primaryStage.setTitle("Styled Progress Bar");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
