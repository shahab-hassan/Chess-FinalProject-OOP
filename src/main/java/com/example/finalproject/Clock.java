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
    int originalMinutes = 10;
    int blackhours = 0;
    int blackMinutes = originalMinutes;
    int blackSeconds = 0;
    int whitehours = 0;
    int whiteMinutes = originalMinutes;
    int whiteSeconds = 0;
    int elapsedTime1 = originalMinutes*60*1000;
    int elapsedTime2 = originalMinutes*60*1000;
    String blackTimeString;
    String whiteTimeString;

    Clock() {
        blackTimeString= String.format("%02d:%02d:%02d", 0, originalMinutes, 0);
        whiteTimeString = String.format("%02d:%02d:%02d", 0, originalMinutes, 0);
        blackClock = new Label();
        whiteClock = new Label();
        initializeBlackClock();
        initializeWhiteClock();
    }

    void initializeBlackClock(){
        blackClock.setText(blackTimeString);
        blackClock.getStyleClass().add("blackClockLabel");
        blackClock.setLayoutX(MyDimensions.blackClockX);
        blackClock.setLayoutY(MyDimensions.blackClockY);
        blackClock.setPrefSize(MyDimensions.clockWidth, MyDimensions.clockHeight);
        blackClock.setBackground(Utilities.applyBackground(MyColors.clockBgDefault));
    }
    void initializeWhiteClock(){
        whiteClock.setText(whiteTimeString);
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
            blackTimeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            blackClock.setText(blackTimeString);
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
            whiteTimeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            whiteClock.setText(whiteTimeString);
        }else{
            stopBothClocks();
            timeEnd();
        }
    }
    void timeEnd() {
        if(GameOver.isTimeOver()) {
            Sounds.gameEndSound();
            Platform.runLater(() -> {
                String winner = Game.chessBoard.isBlackTurn? "White":"Black";
                Alerts.showGameOverAlert("Times Up!", winner + " wins the game... Play Again?");
            });
        }
    }

    void startClock(boolean isBlackTurn) {
        if(Game.chessBoard.ai)
            return;
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
        if(Game.chessBoard.ai){
            blackClock.setOpacity(0.5);
            whiteClock.setOpacity(0.5);
        }
        blackClock.setText(String.format("%02d:%02d:%02d", 0, originalMinutes, 0));
        whiteClock.setText(String.format("%02d:%02d:%02d", 0, originalMinutes, 0));
        elapsedTime1=originalMinutes*60*1000;
        elapsedTime2=originalMinutes*60*1000;
        stopBothClocks();
    }
    void setHoursMinutesSeconds(){
        blackhours = (elapsedTime1 / 3600000);
        blackMinutes = (elapsedTime1 / 60000) % 60;
        blackSeconds = (elapsedTime1 / 1000) % 60;
        blackTimeString = String.format("%02d:%02d:%02d", blackhours, blackMinutes, blackSeconds);
        blackClock.setText(blackTimeString);
        whitehours = (elapsedTime2 / 3600000);
        whiteMinutes = (elapsedTime2 / 60000) % 60;
        whiteSeconds = (elapsedTime2 / 1000) % 60;
        whiteTimeString = String.format("%02d:%02d:%02d", whitehours, whiteMinutes, whiteSeconds);
        whiteClock.setText(whiteTimeString);
    }
}