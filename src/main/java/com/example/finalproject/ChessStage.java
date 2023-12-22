package com.example.finalproject;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

class ChessStage{
    static Themes themes = new Themes();;
    static Clock clock = new Clock();
    static Kills kills = new Kills();
    ChessStage() {
        Game.root.getChildren().add(themes.themesPanel);
        Game.root.getChildren().add(clock.getWhiteClock());
        Game.root.getChildren().add(clock.getBlackClock());
        Game.root.getChildren().add(kills.getBlackKillsPanel());
        Game.root.getChildren().add(kills.getWhiteKillsPanel());
    }
    void updateStage(){
        Game.root.getChildren().clear();
        Game.chessBoard.updateChessBoard();
        Game.myStage = new ChessStage();
        Game.root.getChildren().add(Game.chessBoard.board);
    }
    void askForGameMode() {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game Mode");
        alert.setHeaderText("Select Game Mode");
        alert.setContentText("Ai for Single Player and Human for 2 Player");
        ButtonType ai=new ButtonType("AI");
        ButtonType human=new ButtonType("Human");
        alert.getButtonTypes().setAll(ai,human);
        alert.initOwner(Game.primaryStage);

        alert.showAndWait().ifPresent(buttonType -> {
            if(buttonType==ai){
                Game.chessBoard.ai=true;
                Sounds.gameStartSound();
            }
            else if(buttonType==human) {
                Sounds.gameStartSound();
                Game.chessBoard.ai=false;
            }
        });
    }
}