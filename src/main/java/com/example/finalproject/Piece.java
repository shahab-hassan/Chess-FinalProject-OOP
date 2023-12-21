package com.example.finalproject;

import javafx.scene.image.ImageView;

class Piece {
    ImageView icon;
    PieceType type;
    boolean isBlack;
    int px;
    Piece(PieceType type, boolean isBlack, int px) {
        this.type = type;
        this.isBlack = isBlack;
        this.px = px;
        updatePiece();
    }
    void updatePiece() {
        String color = isBlack ? "black" : "white";
        this.icon = new ImageView("file:src/images/"+px+"px/" + type + "_" + color + ".png");
        this.icon.setFitHeight(38);
        this.icon.setFitWidth(38);
    }
}
