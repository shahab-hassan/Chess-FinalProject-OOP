package com.example.finalproject;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class ChessBoard {
    boolean isBlackTurn;
    GridPane board;
    boolean ai;
    ChessBoard(){
        isBlackTurn = false;
        ai = false;
        board = new GridPane();
    }
    void initializeBlocks(){
        for(int r=0; r<8; r++)
            for(int c=0; c<8; c++)
                Game.blocks[r][c] = new Block(r, c);
    }
    void startGame() {
        // Pawns:
        for (int i = 0; i < 8; i++){
            Game.blocks[1][i].setPiece(new Piece(PieceType.PAWN, true, 32));
            Game.blocks[6][i].setPiece(new Piece(PieceType.PAWN, false, 32));
        }

        // Rooks:
        Game.blocks[0][0].setPiece(new Piece(PieceType.ROOK, true, 32));
        Game.blocks[0][7].setPiece(new Piece(PieceType.ROOK, true, 32));
        Game.blocks[7][0].setPiece(new Piece(PieceType.ROOK, false, 32));
        Game.blocks[7][7].setPiece(new Piece(PieceType.ROOK, false, 32));

        // Knights:
        Game.blocks[0][1].setPiece(new Piece(PieceType.KNIGHT, true, 32));
        Game.blocks[0][6].setPiece(new Piece(PieceType.KNIGHT, true, 32));
        Game.blocks[7][1].setPiece(new Piece(PieceType.KNIGHT, false, 32));
        Game.blocks[7][6].setPiece(new Piece(PieceType.KNIGHT, false, 32));

        // Bishops:
        Game.blocks[0][2].setPiece(new Piece(PieceType.BISHOP, true, 32));
        Game.blocks[0][5].setPiece(new Piece(PieceType.BISHOP, true, 32));
        Game.blocks[7][2].setPiece(new Piece(PieceType.BISHOP, false, 32));
        Game.blocks[7][5].setPiece(new Piece(PieceType.BISHOP, false, 32));

        // Queen:
        Game.blocks[0][3].setPiece(new Piece(PieceType.QUEEN, true, 32));
        Game.blocks[7][3].setPiece(new Piece(PieceType.QUEEN, false, 32));

        // King:
        Game.blocks[0][4].setPiece(new Piece(PieceType.KING, true, 32));
        Game.blocks[7][4].setPiece(new Piece(PieceType.KING, false, 32));
    }
    void updateChessBoard() {
        board = new GridPane();
        board.setBorder(Utilities.applyBorder(MyColors.darkBlockOnHoverDefault, 4));
        board.setLayoutX(MyDimensions.boardX);
        board.setLayoutY(MyDimensions.boardY);
        board.setPrefSize(MyDimensions.boardWidth, MyDimensions.boardHeight);
        board.setAlignment(Pos.CENTER);

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Game.blocks[r][c].updateBlock();
                board.add(Game.blocks[r][c].getPane(), c, r);
            }
        }
    }
}