package com.example.finalproject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.util.Duration;


public class Clock {
    private Label blackClock;
    private Label whiteClock;
    int elapsedTime1 = 600000;
    int elapsedTime2 = 600000;
    String timeString ;

    Clock() {
        timeString= String.format("%02d:%02d:%02d", 10, 0, 0);
        blackClock = new Label();
        whiteClock = new Label();
        initializeBlackClock();
        initializeWhiteClock();
    }

    void initializeBlackClock(){
        blackClock.setText(timeString);
        blackClock.getStyleClass().add("blackClockLabel");
        blackClock.setLayoutX(MyDimensions.blackClockX);
        blackClock.setLayoutY(MyDimensions.blackClockY);
        blackClock.setPrefSize(MyDimensions.clockWidth, MyDimensions.clockHeight);
        blackClock.setBackground(Utilities.applyBackground(MyColors.clockBgDefault));
    }
    void initializeWhiteClock(){
        whiteClock.setText(timeString);
        whiteClock.getStyleClass().add("whiteClockLabel");
        whiteClock.setLayoutX(MyDimensions.whiteClockX);
        whiteClock.setLayoutY(MyDimensions.whiteClockY);
        whiteClock.setPrefSize(MyDimensions.clockWidth, MyDimensions.clockHeight);
        whiteClock.setBackground(Utilities.applyBackground(MyColors.clockBgDefault));
    }
    Label getBlackClock(){
        return blackClock;
    }
    Label getWhiteClock(){
        return whiteClock;
    }

    Timeline timer1 = new Timeline(new KeyFrame(Duration.seconds(1), event -> blackClockCountDown()));
    Timeline timer2 = new Timeline(new KeyFrame(Duration.seconds(1), event -> whiteClockCountDown()));
    void blackClockCountDown(){
        if(elapsedTime1 > 0){
            elapsedTime1 -= 1000;
            int hours = (elapsedTime1 / 3600000);
            int minutes = (elapsedTime1 / 60000) % 60;
            int seconds = (elapsedTime1 / 1000) % 60;
            timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            blackClock.setText(timeString);
        }else{
            stopBothClocks();
            timeEnd();
        }
    }
    void whiteClockCountDown(){
        if(elapsedTime2 > 0){
            elapsedTime2 -= 1000;
            int hours = (elapsedTime2 / 3600000);
            int minutes = (elapsedTime2 / 60000) % 60;
            int seconds = (elapsedTime2 / 1000) % 60;
            timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            whiteClock.setText(timeString);
        }else{
            stopBothClocks();
            timeEnd();
        }
    }
    void timeEnd() {
        if(GameOver.isTimeOver()) {
            Sounds.gameEndSound();
            Platform.runLater(() -> {
                Utilities.showGameOverAlert("Times Up!");
            });
        }
    }

    void startClock(boolean isBlackTurn) {
        timer1.setCycleCount(Timeline.INDEFINITE);
        timer2.setCycleCount(Timeline.INDEFINITE);
        if (isBlackTurn) {
            timer1.play();
            return;
        }
        timer2.play();
    }
    void stopBothClocks() {
        timer1.stop();
        timer2.stop();
    }
    void resetClock(){
        whiteClock.setText(String.format("%02d:%02d:%02d", 10, 0, 0));
        blackClock.setText(String.format("%02d:%02d:%02d", 10, 0, 0));
        elapsedTime1=600000;
        elapsedTime2=600000;
        stopBothClocks();
    }
}