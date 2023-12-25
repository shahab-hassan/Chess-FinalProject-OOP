package com.example.finalproject;

import javafx.scene.image.ImageView;

import java.io.Serializable;

class Piece{
    ImageView icon;
    PieceType type;
    boolean isBlack;
    int px;
    boolean enPassantRequirement;
    Piece(PieceType type, boolean isBlack, int px) {
        this.type = type;
        this.isBlack = isBlack;
        this.px = px;
        updatePiece();
    }
    void updatePiece() {
        String color = isBlack ? "black" : "white";
        this.icon = new ImageView("file:src/images/"+px+"px/" + type + "_" + color + ".png");
        this.icon.setFitHeight(50);
        this.icon.setFitWidth(50);
        icon.setPreserveRatio(true);
    }
    void setType(PieceType type){
        this.type = type;
    }
}
