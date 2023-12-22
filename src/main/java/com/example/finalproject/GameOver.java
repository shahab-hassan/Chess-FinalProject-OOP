package com.example.finalproject;

import java.util.ArrayList;

class GameOver {
    static boolean isKingInCheck(boolean isBlackKing){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(Game.blocks[i][j].getPiece() == null || Game.blocks[i][j].getPiece().isBlack == isBlackKing)
                    continue;
                ArrayList<int[]> randomMoves = Game.move.getRandomPossibleMoves(Game.blocks[i][j]);
                for(int move[]: randomMoves){
                    if(Game.blocks[move[0]][move[1]].getPiece() != null){
                        if((Game.blocks[move[0]][move[1]].getPiece().type == PieceType.KING)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    static boolean isCheckMate(){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(Game.blocks[i][j].getPiece()==null || Game.blocks[i][j].getPiece().isBlack != Game.chessBoard.isBlackTurn)
                    continue;
                ArrayList<int[]> realMoves = Game.move.getRealPossibleMoves(Game.blocks[i][j]);
                if(realMoves.size() > 0)
                    return false;
            }
        }
        return true;
    }
    static boolean isTimeOver() {
        return ChessStage.clock.elapsedTime1==0 || ChessStage.clock.elapsedTime2==0;
    }
}
