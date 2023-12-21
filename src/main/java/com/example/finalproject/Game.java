package com.example.finalproject;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {

    static Block blocks[][] = new Block[8][8];
    static Move move = new Move();
    static ChessBoard chessBoard = new ChessBoard();
    static Group root = new Group();
    static ChessStage stage = new ChessStage();
    static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        chessBoard.initializeBlocks();
        chessBoard.startGame();
        chessBoard.updateChessBoard();
        root.getChildren().add(chessBoard.board);
        scene = new Scene(root, MyDimensions.frameWidth, MyDimensions.frameHeight);
        scene.setFill(MyColors.darkBlockDefault);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
