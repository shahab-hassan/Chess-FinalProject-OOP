package com.example.finalproject;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

class ChessStage{
    static Themes themes = new Themes();;
    static Clock clock = new Clock();
    static Kills kills = new Kills();
    static ChessBtns chessBtns = new ChessBtns();
    ChessStage() {
        Game.root.getChildren().add(themes.themesPanel);
        Game.root.getChildren().add(clock.getWhiteClock());
        Game.root.getChildren().add(clock.getBlackClock());
        Game.root.getChildren().add(kills.getBlackKillsPanel());
        Game.root.getChildren().add(kills.getWhiteKillsPanel());
        Game.root.getChildren().add(chessBtns.chessBtnsPane);
        Game.root.getChildren().add(chessBtns.undoRedoPane);
        Game.player.showPlayersMark();
        Game.root.getChildren().add(Game.player.getPane());
        GameAnalysis.initializeAnalysisBar();
    }
    void updateStage(){
        GameAnalysis.controlProgressBar();
        Game.root.getChildren().clear();
        Game.chessBoard.updateChessBoard();
        Game.myStage = new ChessStage();
        Game.player.currentPlayer();
        ChessStage.clock.stopBothClocks();
        ChessStage.clock.startClock(Game.chessBoard.isBlackTurn);
        Game.root.getChildren().add(Game.chessBoard.board);
        Game.root.getChildren().add(GameAnalysis.progressBar);


    }
    void startMainGame(){
        SaveGame.isGameSaved = true;
        Game.chessBoard.isBlackTurn = false;
        ChessStage.chessBtns.isGameStart = false;
        ChessStage.clock.resetClock();
        ChessStage.kills.resetKills();
        ChessStage.chessBtns.updateUndoRedoBtns();
        Game.blocks = new Block[8][8];
        Game.chessBoard.initializeBlocks();
        Game.chessBoard.startGame();
        Game.myStage.updateStage();
        Sounds.gameStartSound();
        ChessStage.clock.startClock(Game.chessBoard.isBlackTurn);
    }
}