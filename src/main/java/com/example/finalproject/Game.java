package com.example.finalproject;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Game extends Application {
    static Block blocks[][] = new Block[8][8];
    static Move move = new Move();
    static ChessBoard chessBoard = new ChessBoard();
    static Group root = new Group();
    static Player player =new Player();
    static ChessStage myStage = new ChessStage();
    static SplashScreen splashScreen = new SplashScreen();
    static Stockfish stockfish;
    static Castle castle = new Castle();
    static EnPassant enPassant = new EnPassant();
    static Scene scene;
    static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Game.primaryStage = primaryStage;
        primaryStage.setScene(splashScreen.logoScene());

        // Adding own Scene:
        scene = new Scene(root, MyDimensions.frameWidth, MyDimensions.frameHeight);
        scene.setFill(MyColors.darkBlockDefault);
        // Loading CSS:
        URL urlCss = new File("src/application.css").toURI().toURL();
        scene.getStylesheets().add(urlCss.toString());

        primaryStage.show();
    }
}
