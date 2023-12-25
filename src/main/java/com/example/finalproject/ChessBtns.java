package com.example.finalproject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.util.Duration;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

public class ChessBtns implements EventHandler<MouseEvent> {
    FlowPane chessBtnsPane;
    Button restartBtn;
    Button mainMenuBtn;
    Button saveGameBtn;
    FlowPane undoRedoPane;
    ImageView undo;
    ImageView redo;
    boolean isGameStart = false;
    boolean isPrevious = true;
    ChessBtns(){
        chessBtnsPane = new FlowPane(0, 15);
        chessBtnsPane.setLayoutX(MyDimensions.chessBtnsPaneX);
        chessBtnsPane.setLayoutY(MyDimensions.chessBtnsPaneY);
        chessBtnsPane.setAlignment(Pos.CENTER);
        chessBtnsPane.setPrefSize(MyDimensions.chessBtnsPaneWidth, MyDimensions.chessBtnsPaneHeight);
        chessBtnsPane.setOrientation(javafx.geometry.Orientation.VERTICAL);

        undoRedoPane = new FlowPane(20, 0);
        undoRedoPane.setLayoutX(MyDimensions.undoRedoPaneX);
        undoRedoPane.setLayoutY(MyDimensions.undoRedoPaneY);
        undoRedoPane.setPrefSize(MyDimensions.undoRedoPaneWidth, MyDimensions.undoRedoPaneHeight);
        undoRedoPane.setAlignment(Pos.CENTER);

        restartBtn = new Button("Restart Game");
        mainMenuBtn = new Button("Main Menu");
        saveGameBtn = new Button("Save Game");

        restartBtn.setOnAction(actionEvent -> restartBtnAction());
        mainMenuBtn.setOnAction(actionEvent -> {
            try {
                mainMenuBtnAction();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        });
        saveGameBtn.setOnAction(actionEvent -> saveGameBtnAction());

        restartBtn.getStyleClass().add("chessBtns");
        mainMenuBtn.getStyleClass().add("chessBtns");
        saveGameBtn.getStyleClass().add("chessBtns");

        undo = new ImageView(new Image("file:src/images/undo.png"));
        redo = new ImageView(new Image("file:src/images/redo.png"));

        undo.setFitHeight(40);
        undo.setFitWidth(40);
        redo.setFitHeight(40);
        redo.setFitWidth(40);
        undo.setCursor(Cursor.HAND);
        redo.setCursor(Cursor.HAND);
        undo.setOpacity(0.4);
        undo.setOnMouseClicked(this);
        redo.setOnMouseClicked(this);
        updateUndoRedoBtns();

        chessBtnsPane.getChildren().addAll(restartBtn, mainMenuBtn, saveGameBtn);
        undoRedoPane.getChildren().addAll(undo, redo);
    }
    void restartBtnAction(){
        if(SaveGame.isGameSaved)
            Alerts.showGameOverAlert("Restart Game", "Are you sure you want to restart?");
        else{
            Alerts.saveGamePrompt();
            Game.myStage.startMainGame();
        }
    }
    void mainMenuBtnAction() throws MalformedURLException {
        if(SaveGame.isGameSaved){
            Alerts.mainMenuPrompt();
        }
        else{
            Alerts.saveGamePrompt();
            Game.primaryStage.setScene(Game.splashScreen.logoScene());
        }
    }
    void saveGameBtnAction(){
        saveGameBtn.setText("Saving...");
        Timeline savedText = new Timeline(new KeyFrame(Duration.seconds(1), event -> saveGameBtn.setText("Saved!")));
        Timeline originalText = new Timeline(new KeyFrame(Duration.seconds(3), event -> saveGameBtn.setText("Save Game")));
        savedText.play();
        originalText.play();
        SaveGame.saveCurrentGame();
    }
    void updateUndoRedoBtns(){
        if(!isGameStart){
            undo.setOpacity(0.4);
            redo.setOpacity(0.4);
            undo.setCursor(Cursor.DEFAULT);
            redo.setCursor(Cursor.DEFAULT);
        }
        else{
            if(isPrevious){
                undo.setOpacity(1);
                undo.setCursor(Cursor.HAND);
                redo.setOpacity(0.4);
                redo.setCursor(Cursor.DEFAULT);
            }
            else{
                undo.setOpacity(0.4);
                undo.setCursor(Cursor.DEFAULT);
                redo.setOpacity(1);
                redo.setCursor(Cursor.HAND);
            }
        }
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED){
            if(mouseEvent.getSource() == undo){
                if(isPrevious){
                    Game.blocks[Move.previousSource.getRow()][Move.previousSource.getCol()].setPiece(Move.previousSource.getPiece());
                    if(Move.previousDestination.getPiece() != null)
                        Game.blocks[Move.previousDestination.getRow()][Move.previousDestination.getCol()].setPiece(Move.previousDestination.getPiece());
                    else
                        Game.blocks[Move.previousDestination.getRow()][Move.previousDestination.getCol()].setPiece(null);
                    Game.myStage.updateStage();
                }
                isPrevious = false;
            }
            else{
                if(!isPrevious){
                    Game.blocks[Move.previousSource.getRow()][Move.previousSource.getCol()].setPiece(null);
                    Game.blocks[Move.previousDestination.getRow()][Move.previousDestination.getCol()].setPiece(Move.previousSource.getPiece());
                    Game.myStage.updateStage();
                }
                isPrevious = true;
            }
            updateUndoRedoBtns();
        }
    }
}
