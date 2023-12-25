package com.example.finalproject;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.Serializable;

public class Block implements EventHandler<MouseEvent> {
    private BorderPane pane;
    private Label label;
    private Piece piece;
    private int row;
    private int col;

    Block() {
        this.pane = null;
        this.label = null;
        this.piece = null;
    }

    Block(int r, int c) {
        this();
        this.row = r;
        this.col = c;
    }

    Block(Block b) {
        this.pane = b.pane;
        this.label = b.label;
        this.piece = b.piece;
        this.row = b.row;
        this.col = b.col;
    }
    void updateBlock() {
        this.pane = new BorderPane();
        this.label = new Label();
        if(Game.blocks[row][col].getPiece() != null)
            this.label.setGraphic(Game.blocks[row][col].piece.icon);
        this.label.setAlignment(Pos.CENTER);
        if ((row + col) % 2 == 0) {
            this.pane.setBackground(Utilities.applyBackground(MyColors.lightBlockDefault));
        } else {
            this.pane.setBackground(Utilities.applyBackground(MyColors.darkBlockDefault));
        }
        this.pane.setPrefSize((double) MyDimensions.boardWidth /8, (double) MyDimensions.boardHeight /8);
        this.pane.setOnMouseClicked(this);
        this.pane.setOnMouseEntered(this);
        this.pane.setOnMouseExited(this);
        this.label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.pane.setCenter(this.label);
    }
    Pane getPane() {
        return this.pane;
    }
    void setLabel(Label label) {
        this.label = label;
    }

    Label getLabel() {
        return this.label;
    }

    void setPiece(Piece piece) {
        this.piece = piece;
    }

    Piece getPiece() {
        return this.piece;
    }

    void setRow(int r) {
        this.row = r;
    }

    void setCol(int c) {
        this.col = c;
    }

    int getRow() {
        return this.row;
    }

    int getCol() {
        return this.col;
    }
    void unSelectAllBlocks(){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++)
                if(Game.blocks[i][j].getLabel().getBorder() != null)
                    Game.blocks[i][j].getLabel().setBorder(null);
        }
    }
    @Override
    public void handle(MouseEvent event) {
        Node node = (Node) event.getSource();

        if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            handleMouseEntered(node);
        } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
            handleMouseExited(node);
        }
        else if(event.getEventType()==MouseEvent.MOUSE_CLICKED){
            mouseClicked();
        }
    }
    public void mouseClicked() {
        if(!ChessStage.chessBtns.isPrevious || (Game.chessBoard.ai &&Game.chessBoard.isBlackTurn))
            return;

        // Empty block:
        if (this.piece == null && this.label.getBorder() == null) {
            unSelectAllBlocks();
            return;
        }

        // Piece Selected to move:
        if (this.piece != null && this.label.getBorder() == null) {
            unSelectAllBlocks();
            if (this.piece.isBlack == Game.chessBoard.isBlackTurn) {
                Move.selectedRow = this.row;
                Move.selectedCol = this.col;
                this.label.setBorder(Utilities.applyBorder(MyColors.selectedPieceBorderDefault, 4));
                Game.move.showPossibleMovesOnBoard(Game.move.getRealPossibleMoves(this));
            }
        }
        //EnPassant moves
        else if (Game.blocks[Move.selectedRow][Move.selectedCol].getPiece().type==PieceType.PAWN &&this.piece == null && this.label.getBorder() != null) {
            Game.enPassant.movePiece(this.row, this.col);
        }
        // Moving piece to possible move block:
        else if (this.piece == null && this.label.getBorder() != null) {
            Game.castle.movePiece(this.row, this.col);
            Sounds.movePieceSound();
        }

        // Killing Piece or Castling:
        else if (this.piece != null && this.label.getBorder() != null) {
            if (this.piece.isBlack != Game.blocks[Move.selectedRow][Move.selectedCol].piece.isBlack) {
                if(this.piece.isBlack)
                    ChessStage.kills.blackKilledPieces[ChessStage.kills.blackKillsCount++] = this.piece;
                else
                    ChessStage.kills.whiteKilledPieces[ChessStage.kills.whiteKillsCount++] = this.piece;
                Sounds.captureSound();
                Game.castle.movePiece(this.row, this.col);
            } else
                unSelectAllBlocks();
        }
    }

    private void handleMouseEntered(Node node) {
        if (node instanceof Region) {
            Region region = (Region) node;
            if (region.getBackground().getFills().get(0).getFill().equals(MyColors.darkBlockDefault)) {
                region.setBackground(Utilities.applyBackground(MyColors.darkBlockOnHoverDefault));
            } else {
                region.setBackground(Utilities.applyBackground(MyColors.lightBlockOnHoverDefault));
            }
        }
    }

    private void handleMouseExited(Node node) {
        if (node instanceof Region) {
            Region region = (Region) node;
            if (region.getBackground().getFills().get(0).getFill().equals(MyColors.darkBlockOnHoverDefault)) {
                region.setBackground(Utilities.applyBackground(MyColors.darkBlockDefault));
            } else {
                region.setBackground(Utilities.applyBackground(MyColors.lightBlockDefault));
            }
        }
    }
}
