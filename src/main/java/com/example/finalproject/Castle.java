package com.example.finalproject;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;

class Castle extends Move implements specialMovesMethods{
    boolean canBlackShortCastle = true;
    boolean canBlackLongCastle = true;
    boolean canWhiteShortCastle = true;
    boolean canWhiteLongCastle = true;
    @Override
    public void movePiece(int toRow, int toCol){
        SaveGame.isGameSaved = false;
        Block selectedBlock = Game.blocks[Move.selectedRow][Move.selectedCol];
        Block destinationBlock = Game.blocks[toRow][toCol];
        BorderStroke borderStroke = (destinationBlock.getLabel().getBorder()).getStrokes().get(0);
        if (borderStroke.getBottomStroke() == MyColors.castleMoveBorderDefault) {
            if(selectedBlock.getPiece().isBlack){
                updateCastleStatus();
                castleBlack(toCol);
            }
            else{
                updateCastleStatus();
                castleWhite(toCol);
            }
            Sounds.castleSound();
            Game.chessBoard.isBlackTurn = !Game.chessBoard.isBlackTurn;
            ChessStage.clock.stopBothClocks();
            ChessStage.clock.startClock(Game.chessBoard.isBlackTurn);
            Game.myStage.updateStage();
            if(Game.chessBoard.ai && Game.chessBoard.isBlackTurn){
                StockfishWorker worker = new StockfishWorker();
                Thread thread = new Thread(worker);
                thread.setDaemon(true);
                thread.start();
            }
        }
        else{
            updateCastleStatus();
            super.movePiece(toRow, toCol);
        }
    }
    void updateCastleStatus(){
        Piece selectedPiece = Game.blocks[Move.selectedRow][Move.selectedCol].getPiece();
        if(selectedPiece.isBlack){
            if(selectedPiece.type == PieceType.KING)
                canBlackLongCastle = canBlackShortCastle = false;
            else if(selectedPiece.type == PieceType.ROOK){
                if(Move.selectedCol == 0)
                    canBlackLongCastle = false;
                else if(Move.selectedCol == 7)
                    canBlackShortCastle = false;
            }
        }
        else{
            if(selectedPiece.type == PieceType.KING)
                canWhiteLongCastle = canWhiteShortCastle = false;
            else if(selectedPiece.type == PieceType.ROOK){
                if(Move.selectedCol == 0)
                    canWhiteLongCastle = false;
                else if(Move.selectedCol == 7)
                    canWhiteShortCastle = false;
            }
        }
    }
    boolean checkCanBlackShortCastle(){
        if(canBlackShortCastle){
            if(GameOver.isKingInCheck(Game.blocks[0][4].getPiece().isBlack))
                return false;
            for(int i = 5; i<7; i++){
                if(Game.blocks[0][i].getPiece() != null)
                    return false;
                else if(!isSafeMove(Game.blocks[0][4], 0, i))
                    return false;
            }
        }
        else
            return false;
        return Game.blocks[0][7].getPiece()!=null && Game.blocks[0][7].getPiece().isBlack;
    }
    boolean checkCanBlackLongCastle(){
        if(canBlackLongCastle){
            if(GameOver.isKingInCheck(Game.blocks[0][4].getPiece().isBlack))
                return false;
            for(int i = 3; i>0; i--){
                if(Game.blocks[0][i].getPiece() != null)
                    return false;
                else if(!isSafeMove(Game.blocks[0][4], 0, i))
                    return false;
            }
        }
        else
            return false;
        return Game.blocks[0][0].getPiece()!=null && Game.blocks[0][0].getPiece().isBlack;
    }
    boolean checkCanWhiteShortCastle(){
        if(canWhiteShortCastle){
            if(GameOver.isKingInCheck(Game.blocks[7][4].getPiece().isBlack))
                return false;
            for(int i = 5; i<7; i++){
                if(Game.blocks[7][i].getPiece() != null)
                    return false;
                if(!isSafeMove(Game.blocks[7][4], 7, i))
                    return false;
            }
        }
        else
            return false;
        return Game.blocks[7][7].getPiece()!=null && !Game.blocks[7][7].getPiece().isBlack;
    }
    boolean checkCanWhiteLongCastle(){
        if(canWhiteLongCastle){
            if(GameOver.isKingInCheck(Game.blocks[7][4].getPiece().isBlack))
                return false;
            for(int i = 3; i>0; i--){
                if(Game.blocks[7][i].getPiece() != null)
                    return false;
                if(!isSafeMove(Game.blocks[7][4], 7, i))
                    return false;
            }
        }
        else
            return false;
        return Game.blocks[7][0].getPiece()!=null && !Game.blocks[7][0].getPiece().isBlack;
    }
    public void showMoves(){
        Piece selectedPiece = Game.blocks[Move.selectedRow][Move.selectedCol].getPiece();
        if(selectedPiece.type == PieceType.KING){
            if(selectedPiece.isBlack){
                if(checkCanBlackShortCastle() )
                    Game.blocks[0][6].getLabel().setBorder(Utilities.applyBorder(MyColors.castleMoveBorderDefault, 5));
                else if(checkCanBlackLongCastle() && Game.blocks[0][0].getPiece().isBlack)
                    Game.blocks[0][2].getLabel().setBorder(Utilities.applyBorder(MyColors.castleMoveBorderDefault, 5));
            }
            else{
                if(checkCanWhiteShortCastle() )
                    Game.blocks[7][6].getLabel().setBorder(Utilities.applyBorder(MyColors.castleMoveBorderDefault, 5));
                else if(checkCanWhiteLongCastle())
                    Game.blocks[7][2].getLabel().setBorder(Utilities.applyBorder(MyColors.castleMoveBorderDefault, 5));
            }
        }
    }
    public boolean isPossible(){
        return checkCanBlackLongCastle() || checkCanBlackShortCastle() || checkCanWhiteLongCastle() || checkCanWhiteShortCastle();
    }
    void castleBlack(int toCol){
        if(toCol == 6){
            Game.blocks[0][6].setPiece(Game.blocks[0][4].getPiece());
            Game.blocks[0][4].setPiece(null);
            Game.blocks[0][5].setPiece(Game.blocks[0][7].getPiece());
            Game.blocks[0][7].setPiece(null);
        }
        else if(toCol == 2){
            Game.blocks[0][2].setPiece(Game.blocks[0][4].getPiece());
            Game.blocks[0][4].setPiece(null);
            Game.blocks[0][3].setPiece(Game.blocks[0][0].getPiece());
            Game.blocks[0][0].setPiece(null);
        }
    }
    void castleWhite(int toCol){
        if(toCol == 6){
            Game.blocks[7][6].setPiece(Game.blocks[7][4].getPiece());
            Game.blocks[7][4].setPiece(null);
            Game.blocks[7][5].setPiece(Game.blocks[7][7].getPiece());
            Game.blocks[7][7].setPiece(null);
        }
        else if(toCol == 2){
            Game.blocks[7][2].setPiece(Game.blocks[7][4].getPiece());
            Game.blocks[7][4].setPiece(null);
            Game.blocks[7][3].setPiece(Game.blocks[7][0].getPiece());
            Game.blocks[7][0].setPiece(null);
        }
    }
}
