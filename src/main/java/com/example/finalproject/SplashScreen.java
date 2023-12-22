package com.example.finalproject;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class SplashScreen implements EventHandler<MouseEvent> {
    private static final int LOGO_X = 500;
    private static final int LOGO_Y = 200;
    private static final int LOGO_FIT_WIDTH = 400;
    private static final int LOGO_FIT_HEIGHT = 400;
    private static final int RECTANGLE_X = 480;
    private static final int RECTANGLE_Y = 340;
    private static final int RECTANGLE_HEIGHT = 90;
    private static final int RECTANGLE_WIDTH = 350;
    private static final int TEXT_Y = 400;
    private static final int CHESS_TEXT_X = 140;
    private static final int GAME_TEXT_X = 490;
    private static final double ARC_WIDTH = 20.0;
    private static final double ARC_HEIGHT = 20.0;
    private static Button button = new Button("Start Game");

    public Scene logoScene() throws MalformedURLException {
        Group root = new Group();
        Scene scene = new Scene(root, MyDimensions.frameWidth, MyDimensions.frameHeight, Color.LAVENDER);
        URL urlCss = new File("src/application.css").toURI().toURL();
        scene.getStylesheets().add(urlCss.toString());
        button.setLayoutX(450);
        button.setLayoutY(500);
        button.getStyleClass().add("startGameBtn");
        button.setOnMouseClicked(this);
        button.setOnMouseEntered(this);
        button.setOnMouseExited(this);

        Image image = new Image("file:src/images/logo.png");
        ImageView imageView = new ImageView(image);
        imageView.setX(LOGO_X);
        imageView.setY(LOGO_Y);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(LOGO_FIT_WIDTH);
        imageView.setFitHeight(LOGO_FIT_HEIGHT);

        FadeTransition logoFadeTransition = createFadeTransition(imageView, 0.0, 1.0, Duration.seconds(1.5));

        Rectangle rec = new Rectangle(RECTANGLE_X, RECTANGLE_Y, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
        rec.setFill(Color.rgb(14, 13, 56));
        rec.setArcWidth(ARC_WIDTH);
        rec.setArcHeight(ARC_HEIGHT);

        Text chessText = new Text(CHESS_TEXT_X, TEXT_Y, "Knight’s");
        chessText.setFont(Font.font("Matura MT Script Capitals", 80));
        chessText.setFill(Color.rgb(235, 79, 52));

        Text gameText = new Text(GAME_TEXT_X, TEXT_Y, "Odyssey");
        gameText.setFont(Font.font("Matura MT Script Capitals", 80));
        gameText.setFill(Color.WHITESMOKE);

        Group logoGroup = new Group(imageView);
        Group textGroup = new Group(rec, chessText, gameText);

        root.getChildren().add(logoGroup);

        logoFadeTransition.play();
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));
        pauseTransition.setOnFinished(event -> {
            TranslateTransition logoTranslateTransition = createTranslateTransition(logoGroup, Duration.seconds(1.5), 300, -40);
            ScaleTransition scaleTransition = createScaleTransition(logoGroup, Duration.seconds(1.5), 1.0, 1.0, 0.5, 0.5);
            FadeTransition textFadeTransition = createFadeTransition(textGroup, 0.0, 1.0, Duration.seconds(1.5));
            FadeTransition buttonFadeTransition = createFadeTransition(button, 0.0, 1.0, Duration.seconds(1));
            root.getChildren().add(textGroup);
            root.getChildren().add(button);
            SequentialTransition sequentialTransition = new SequentialTransition();
            ParallelTransition parallelTransition = new ParallelTransition(logoTranslateTransition, scaleTransition);
            sequentialTransition.getChildren().addAll(parallelTransition, textFadeTransition, buttonFadeTransition);
            sequentialTransition.play();

            textFadeTransition.setOnFinished(fadeEvent -> {


            });
        });
        pauseTransition.play();
        return scene;
    }


    private FadeTransition createFadeTransition(Node node, double fromValue, double toValue, Duration duration) {
        FadeTransition fadeTransition = new FadeTransition(duration, node);
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setToValue(toValue);
        return fadeTransition;
    }

    private TranslateTransition createTranslateTransition(Node node, Duration duration, double toX, double toY) {
        TranslateTransition translateTransition = new TranslateTransition(duration, node);
        translateTransition.setToX(toX);
        translateTransition.setToY(toY);
        return translateTransition;
    }

    private ScaleTransition createScaleTransition(Node node, Duration duration, double fromX, double fromY, double toX, double toY) {
        ScaleTransition scaleTransition = new ScaleTransition(duration, node);
        scaleTransition.setFromX(fromX);
        scaleTransition.setFromY(fromY);
        scaleTransition.setToX(toX);
        scaleTransition.setToY(toY);
        return scaleTransition;
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            button.setCursor(Cursor.HAND);
            button.getStyleClass().remove("startGameBtn");
            button.getStyleClass().add("startGameBtnHover");
        }
        if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
            button.getStyleClass().remove("startGameBtnHover");
            button.getStyleClass().add("startGameBtn");
        }
        if(event.getEventType() == MouseEvent.MOUSE_CLICKED){
            Game.myStage.askForGameMode();
            Game.primaryStage.setScene(Game.scene);
            Game.chessBoard.initializeBlocks();
            Game.chessBoard.startGame();
            Game.chessBoard.updateChessBoard();
            Game.root.getChildren().add(Game.chessBoard.board);
        }
    }
}
