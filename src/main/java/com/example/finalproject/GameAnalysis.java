package com.example.finalproject;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.util.Duration;



public class GameAnalysis {
    static ProgressBar progressBar = new ProgressBar();
    static  void initializeAnalysisBar()
    {
        progressBar.setPrefWidth(280);
        progressBar.setLayoutX(MyDimensions.chessBtnsPaneX+30);
        progressBar.setLayoutY(MyDimensions.chessBtnsPaneY+320);
        progressBar.setPrefHeight(20);
        progressBar.getStyleClass().add("progress-bar");
    }
    static void controlProgressBar()
    {
        animateProgressBar(progressBarCalculations());

    }
    static void animateProgressBar(double targetValue) {
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), new KeyValue(progressBar.progressProperty(), targetValue));
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
    static double progressBarCalculations() {
        double value = Game.stockfish.convertPositionToInt();

        double minValue = -10.0;
        double maxValue = 10.0;

        value = Math.max(minValue, Math.min(maxValue, value));

        return (value - minValue) / (maxValue - minValue);
    }


}

