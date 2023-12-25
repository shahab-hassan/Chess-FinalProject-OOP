package com.example.finalproject;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Player {
    private Pane pane = new Pane();
    private Text text1 = new Text("Player 1");
    private Text text2 = new Text("Player 2");

    public Pane getPane() {
        return pane;
    }

    public Text getText1() {
        return text1;
    }

    public Text getText2() {
        return text2;
    }

    void showPlayersMark() {
        pane.getChildren().clear();

        text1.setFill(Color.BLACK);
        text1.setFont(Font.font("Matura MT Script Capitals", FontWeight.NORMAL, 40));

        text2.setFill(Color.BLACK);
        text2.setFont(Font.font("Matura MT Script Capitals", FontWeight.NORMAL, 40));

        text1.setOpacity(0.4);
        text2.setOpacity(0.4);
        text1.setX(10);
        text1.setY(500);
        text2.setX(10);
        text2.setY(10);

        pane.getChildren().addAll(text1, text2);

        pane.setPrefSize(200, 1000);
        pane.setLayoutX(1200);
        pane.setLayoutY(MyDimensions.chessBtnsPaneY);
    }
    void currentPlayer() {
        pane.getChildren().clear();

        if (Game.chessBoard.isBlackTurn) {
            text1.setOpacity(0.4);
            text1.setFont(Font.font("Matura MT Script Capitals", FontWeight.NORMAL, 40));
            text1.setFill(Color.BLACK);

            text2.setOpacity(1.0);
            text2.setFont(Font.font("Matura MT Script Capitals", FontWeight.BOLD, 40));
            text2.setFill(Color.RED);
        } else {
            text2.setOpacity(0.4);
            text2.setFont(Font.font("Matura MT Script Capitals", FontWeight.NORMAL, 40));
            text2.setFill(Color.BLACK);

            text1.setOpacity(1.0);
            text1.setFont(Font.font("Matura MT Script Capitals", FontWeight.BOLD, 40));
            text1.setFill(Color.RED);
        }

        pane.getChildren().addAll(text1, text2);
        pane.setPrefSize(200, 1000);
        pane.setLayoutX(1200);
        pane.setLayoutY(MyDimensions.chessBtnsPaneY);
    }

}
