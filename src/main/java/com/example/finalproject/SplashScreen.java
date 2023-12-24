package com.example.finalproject;

import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class SplashScreen implements EventHandler<MouseEvent> {
    private static final int LOGO_X = 500;
    private static final int LOGO_Y = 200;
    private static final int LOGO_FIT_WIDTH = 400;
    private static final int LOGO_FIT_HEIGHT = 400;
    private static final int RECTANGLE_X = 480;
    private static final int RECTANGLE_Y = 185;
    private static final int RECTANGLE_HEIGHT = 90;
    private static final int RECTANGLE_WIDTH = 350;
    private static final int TEXT_Y = 245;
    private static final int CHESS_TEXT_X = 140;
    private static final int GAME_TEXT_X = 490;
    private static final double ARC_WIDTH = 20.0;
    private static final double ARC_HEIGHT = 20.0;
    CheckBox aiCB = new CheckBox("AI");
    CheckBox humanCB = new CheckBox("2 Player");
    ComboBox<String> gameDurations = new ComboBox<>();
    Label durationText = new Label("Duration: ");
    private static Button button = new Button("Start New Game");
    private static Button continueBtn = new Button("Continue Saved Game");

    public Scene logoScene() throws MalformedURLException {

        Group root = new Group();
        Scene scene = new Scene(root, MyDimensions.frameWidth, MyDimensions.frameHeight, Color.LAVENDER);
        URL urlCss = new File("src/application.css").toURI().toURL();
        scene.getStylesheets().add(urlCss.toString());

        button.setLayoutX(450);
        button.setLayoutY(530);
        button.getStyleClass().add("startGameBtn");
        button.setOnMouseClicked(this);
        button.setOnMouseEntered(this);
        button.setOnMouseExited(this);

        continueBtn.setLayoutX(460);
        continueBtn.setLayoutY(620);
        continueBtn.getStyleClass().add("continueBtn");
        continueBtn.setOnMouseClicked(this);

        aiCB.setLayoutX(460);
        aiCB.setLayoutY(370);
        aiCB.getStyleClass().add("checkBox");
        aiCB.setSelected(true);
        humanCB.setLayoutX(540);
        humanCB.setLayoutY(370);
        humanCB.getStyleClass().add("checkBox");
        humanCB.setSelected(false);
        aiCB.setOnMouseClicked(this);
        humanCB.setOnMouseClicked(this);

        gameDurations.setItems(FXCollections.observableArrayList(
                "5 minutes", "10 minutes", "15 minutes", "30 minutes", "60 minutes"
        ));
        gameDurations.getSelectionModel().select("10 minutes");
        gameDurations.setLayoutX(570);
        gameDurations.setLayoutY(445);
        durationText.setLayoutX(450);
        durationText.setLayoutY(430);
        durationText.getStyleClass().add("durationTextLabel");

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
            TranslateTransition logoTranslateTransition = createTranslateTransition(logoGroup, Duration.seconds(1), 300, -180);
            ScaleTransition scaleTransition = createScaleTransition(logoGroup, Duration.seconds(1), 1.0, 1.0, 0.5, 0.5);
            FadeTransition textFadeTransition = createFadeTransition(textGroup, 0.0, 1.0, Duration.seconds(1));
            FadeTransition buttonFadeTransition = createFadeTransition(button, 0.0, 1.0, Duration.seconds(1));
            FadeTransition aiCBFadeTransition = createFadeTransition(aiCB, 0.0, 1.0, Duration.seconds(0.2));
            FadeTransition humanCBFadeTransition = createFadeTransition(humanCB, 0.0, 1.0, Duration.seconds(0.05));
            FadeTransition gameDurationsFadeTransition = createFadeTransition(gameDurations, 0.0, 1.0, Duration.seconds(0.05));
            FadeTransition durationTextFadeTransition = createFadeTransition(durationText, 0.0, 1.0, Duration.seconds(0.05));
            FadeTransition continueBtnFadeTransition = createFadeTransition(continueBtn, 0.0, 1.0, Duration.seconds(0.05));
            root.getChildren().add(textGroup);
            root.getChildren().add(aiCB);
            root.getChildren().add(humanCB);
            root.getChildren().add(button);
            root.getChildren().add(gameDurations);
            root.getChildren().add(durationText);
            root.getChildren().add(continueBtn);
            SequentialTransition sequentialTransition = new SequentialTransition();
            ParallelTransition parallelTransition = new ParallelTransition(logoTranslateTransition, scaleTransition);
            sequentialTransition.getChildren().addAll(parallelTransition, textFadeTransition, aiCBFadeTransition, humanCBFadeTransition, durationTextFadeTransition, gameDurationsFadeTransition, buttonFadeTransition, continueBtnFadeTransition);
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
        if(event.getEventType() == MouseEvent.MOUSE_CLICKED){
            if(event.getSource() == aiCB){
                if(humanCB.isSelected())
                    humanCB.setSelected(false);
                aiCB.setSelected(true);
            }
            else if(event.getSource() == humanCB){
                if(aiCB.isSelected())
                    aiCB.setSelected(false);
                humanCB.setSelected(true);
            }
            else if(event.getSource() == continueBtn){
                if(SaveGame.isSavedGameAvailable())
                    SaveGame.loadSavedGame();
                else
                    Alerts.noGameSavedPrompt(humanCB, gameDurations);
            }
            else{
                if(humanCB.isSelected())
                    Game.chessBoard.ai = false;
                else
                    Game.chessBoard.ai = true;
                int minutes = Integer.parseInt(gameDurations.getSelectionModel().getSelectedItem().split(" ")[0]);
                ChessStage.clock.originalMinutes = minutes;
                Game.myStage.startMainGame();
                Game.primaryStage.setScene(Game.scene);
            }

        }
    }
}
