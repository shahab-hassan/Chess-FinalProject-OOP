package com.example.finalproject;

import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class Kills {
    int blackKillsCount;
    int whiteKillsCount;
    Piece whiteKilledPieces[];
    Piece blackKilledPieces[];
    Kills(){
        blackKillsCount = 0;
        whiteKillsCount = 0;
        whiteKilledPieces = new Piece[16];
        blackKilledPieces = new Piece[16];
    }
    FlowPane getBlackKillsPanel(){
        FlowPane blackKillsPanel = new FlowPane(0, 0);
        blackKillsPanel.setLayoutX(MyDimensions.whiteKillsPanelX);
        blackKillsPanel.setLayoutY(MyDimensions.whiteKillsPanelY);
        blackKillsPanel.setPrefSize(MyDimensions.blackKillsPanelWidth, MyDimensions.blackKillsPanelHeight);
        if(blackKillsCount == 0){
            Label label = new Label("NO KILLS YET!");
            label.getStyleClass().add("noKillsYetLabel");
            blackKillsPanel.setAlignment(Pos.CENTER);
            blackKillsPanel.getChildren().add(label);
            return blackKillsPanel;
        }
        for(int i=0; i<16; i++){
            Label blackKillsLabel = new Label();
            if(blackKilledPieces[i] != null){
                Piece piece = new Piece(blackKilledPieces[i].type, blackKilledPieces[i].isBlack, 32);
                ImageView icon = piece.icon;
                icon.setFitWidth(20);
                icon.setFitHeight(20);
                blackKillsLabel.setGraphic(icon);
            }
            blackKillsPanel.getChildren().add(blackKillsLabel);
        }
        return blackKillsPanel;
    }
    FlowPane getWhiteKillsPanel(){
        FlowPane whiteKillsPanel = new FlowPane();
        whiteKillsPanel.setLayoutX(MyDimensions.blackKillsPanelX);
        whiteKillsPanel.setLayoutY(MyDimensions.blackKillsPanelY);
        whiteKillsPanel.setPrefSize(MyDimensions.whiteKillsPanelWidth, MyDimensions.whiteKillsPanelHeight);
        if(whiteKillsCount == 0){
            Label label = new Label("NO KILLS YET!");
            label.getStyleClass().add("noKillsYetLabel");
            whiteKillsPanel.setAlignment(Pos.CENTER);
            whiteKillsPanel.getChildren().add(label);
            return whiteKillsPanel;
        }
        for(int i=0; i<16; i++){
            Label whiteKillsLabel = new Label();
            if(whiteKilledPieces[i] != null){
                Piece piece = new Piece(whiteKilledPieces[i].type, whiteKilledPieces[i].isBlack, 32);
                ImageView icon = piece.icon;
                icon.setFitWidth(20);
                icon.setFitHeight(20);
                whiteKillsLabel.setGraphic(icon);
            }
            whiteKillsPanel.getChildren().add(whiteKillsLabel);
        }
        return whiteKillsPanel;
    }
    void resetKills(){
        blackKillsCount = 0;
        whiteKillsCount = 0;
        whiteKilledPieces = new Piece[16];
        blackKilledPieces = new Piece[16];
    }
}
