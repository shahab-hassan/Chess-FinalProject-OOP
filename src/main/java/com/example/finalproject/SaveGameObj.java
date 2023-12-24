package com.example.finalproject;

import java.io.Serializable;

public class SaveGameObj implements Serializable {
    PieceObj pieceObj;
    int row;
    int col;
    SaveGameObj(PieceObj pieceObj, int row, int col){
        this.pieceObj = pieceObj;
        this.row = row;
        this.col = col;
    }
}

class PieceObj implements Serializable{
    PieceType pieceType;
    boolean isBlack;
    PieceObj(PieceType type, boolean isBlack){
        this.pieceType = type;
        this.isBlack = isBlack;
    }
}
