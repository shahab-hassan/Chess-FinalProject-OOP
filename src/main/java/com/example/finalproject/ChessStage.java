package com.example.finalproject;

import javafx.scene.Group;
import javafx.scene.control.Label;

class ChessStage{
    static Themes themes;
    ChessStage() {
        themes = new Themes();
        Game.root.getChildren().add(themes.themesPanel);
    }
    void updateStage(){
        Game.root.getChildren().clear();
        Game.chessBoard.updateChessBoard();
        Game.stage = new ChessStage();
        Game.root.getChildren().add(Game.chessBoard.board);
        if(GameOver.isKingInCheck(Game.chessBoard.isBlackTurn))
            GameSounds.moveCheckSound();
    }
}