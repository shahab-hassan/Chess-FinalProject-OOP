package com.example.finalproject;

import javafx.scene.layout.*;

public class EnPassant extends Move implements specialMovesMethods{
    boolean leftSideEnpassant=false;
    boolean rightSideEnpassant=false;
    public boolean isPossible()
    {
        Block block=Game.blocks[Move.selectedRow][Move.selectedCol];
        int row = block.getRow();
        int col = block.getCol();

        Piece whiteLeftNeighbor = Utilities.isInBoard(row, col - 1) ? Game.blocks[row][col - 1].getPiece() : null;
        Piece whiteRightNeighbor = Utilities.isInBoard(row, col + 1) ? Game.blocks[row][col + 1].getPiece() : null;
        Piece blackLeftNeighbor = Utilities.isInBoard(row, col + 1) ? Game.blocks[row][col + 1].getPiece() : null;
        Piece blackRightNeighbor = Utilities.isInBoard(row, col - 1) ? Game.blocks[row][col - 1].getPiece() : null;
        if(block.getPiece().type==PieceType.PAWN && !block.getPiece().isBlack)
        {

            if(whiteLeftNeighbor!=null && whiteLeftNeighbor.type==PieceType.PAWN && whiteLeftNeighbor.isBlack!=block.getPiece().isBlack && whiteLeftNeighbor.enPassantRequirement)
            {
                leftSideEnpassant=true;
                return true;
            }
            if(whiteRightNeighbor!=null && whiteRightNeighbor.type==PieceType.PAWN && whiteRightNeighbor.isBlack!=block.getPiece().isBlack && whiteRightNeighbor.enPassantRequirement)
            {
                rightSideEnpassant=true;
                return true;
            }



        }
        if(block.getPiece().type==PieceType.PAWN && block.getPiece().isBlack){
            if(blackLeftNeighbor!=null && blackLeftNeighbor.type==PieceType.PAWN && blackLeftNeighbor.isBlack!=block.getPiece().isBlack && blackLeftNeighbor.enPassantRequirement)
            {
                leftSideEnpassant=true;
                return true;
            }
            if(blackRightNeighbor!=null && blackRightNeighbor.type==PieceType.PAWN && blackRightNeighbor.isBlack!=block.getPiece().isBlack && blackRightNeighbor.enPassantRequirement)
            {
                rightSideEnpassant=true;
                return true;
            }
        }

        leftSideEnpassant=false;
        rightSideEnpassant=false;
        return false;
    }
    @Override
    public void movePiece(int toRow, int toCol){
        if(toRow==selectedRow+2 || toRow==selectedRow-2){
            Game.blocks[selectedRow][selectedCol].getPiece().enPassantRequirement=true;
        }

        else{
            Game.enPassant.resetEnpassantRequirement();
        }

        Block selectedBlock = Game.blocks[selectedRow][selectedCol];
        if((toRow==selectedRow+1 || toRow==selectedRow-1)&& (toCol==selectedCol+1 || toCol==selectedCol-1))
        {
            Game.blocks[selectedRow][toCol].setPiece(null);
            Sounds.captureSound();

        }
        Block destinationBlock = Game.blocks[toRow][toCol];
        destinationBlock.setPiece(selectedBlock.getPiece());
        selectedBlock.setPiece(null);
        Sounds.movePieceSound();
        Game.chessBoard.isBlackTurn = !Game.chessBoard.isBlackTurn;
        Game.myStage.updateStage();
        if (Game.chessBoard.ai && Game.chessBoard.isBlackTurn) {
            StockfishWorker worker = new StockfishWorker();
            Thread thread = new Thread(worker);
            thread.setDaemon(true);
            thread.start();
        }




    }
    public void showMoves()
    {
        if(isPossible())
        {
            if(leftSideEnpassant){

                if(Game.blocks[selectedRow][selectedCol].getPiece().isBlack){

                    if(Game.blocks[selectedRow+1][selectedCol+1].getPiece()==null){
                        Game.blocks[selectedRow+1][selectedCol+1].getLabel().setBorder(Utilities.applyBorder(MyColors.dangerPieceBorderRed, 3));

                    }
                }else if(!Game.blocks[selectedRow][selectedCol].getPiece().isBlack){

                    if(Game.blocks[selectedRow-1][selectedCol-1].getPiece()==null){
                        Game.blocks[selectedRow-1][selectedCol-1].getLabel().setBorder(Utilities.applyBorder(MyColors.dangerPieceBorderRed, 3));

                    }
                }
            }
            if(rightSideEnpassant){
                if(Game.blocks[selectedRow][selectedCol].getPiece().isBlack){
                    if(Game.blocks[selectedRow+1][selectedCol-1].getPiece()==null){
                        Game.blocks[selectedRow+1][selectedCol-1].getLabel().setBorder(Utilities.applyBorder(MyColors.dangerPieceBorderRed, 3));

                    }
                }else if(!Game.blocks[selectedRow][selectedCol].getPiece().isBlack){
                    if(Game.blocks[selectedRow-1][selectedCol+1].getPiece()==null){
                        Game.blocks[selectedRow-1][selectedCol+1].getLabel().setBorder(Utilities.applyBorder(MyColors.dangerPieceBorderRed, 3));

                    }
                }
            }



        }
    }
    void resetEnpassantRequirement()
    {
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
            {
                if(Game.blocks[i][j].getPiece()!=null)
                {
                    Game.blocks[i][j].getPiece().enPassantRequirement=false;
                }
            }
    }
}
