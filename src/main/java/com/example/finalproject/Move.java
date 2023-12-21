package com.example.finalproject;

import java.util.ArrayList;

class Move{
    static int selectedRow = -1;
    static int selectedCol = -1;

    ArrayList<int[]> getRandomPossibleMoves(Block clickedBlock){
        ArrayList<int[]> randomMoves = new ArrayList<>();
        if(clickedBlock.getPiece().type == PieceType.ROOK)
            randomMoves = calculateRookMoves(clickedBlock);
        else if(clickedBlock.getPiece().type == PieceType.BISHOP)
            randomMoves = calculateBishopMoves(clickedBlock);
        else if(clickedBlock.getPiece().type == PieceType.QUEEN){
            randomMoves = calculateQueenMoves(clickedBlock);
        }
        else if(clickedBlock.getPiece().type == PieceType.KNIGHT){
            randomMoves = calculateKnightMoves(clickedBlock);
        }
        else if(clickedBlock.getPiece().type == PieceType.KING){
            randomMoves = calculateKingMoves(clickedBlock);
        }
        else if(clickedBlock.getPiece().type == PieceType.PAWN)
            randomMoves = calculatePawnMoves(clickedBlock);
        return randomMoves;
    }

    ArrayList<int[]> getRealPossibleMoves(Block clickedBlock){
        ArrayList<int[]> randomMoves = getRandomPossibleMoves(clickedBlock);
        ArrayList<int[]> realMoves = new ArrayList<>();
        for(int move[]: randomMoves){
            int endRow = move[0];
            int endCol = move[1];
            if(isSafeMove(clickedBlock, endRow, endCol)){
                realMoves.add(move);
            }
        }
        return realMoves;
    }

    void showPossibleMovesOnBoard(ArrayList<int[]> possibleMoves){
        for(int move[]: possibleMoves){
            if(Game.blocks[move[0]][move[1]].getPiece() != null){
                if(Game.blocks[move[0]][move[1]].getPiece().isBlack != Game.blocks[selectedRow][selectedCol].getPiece().isBlack)
                    Game.blocks[move[0]][move[1]].getLabel().setBorder(Utilities.applyBorder(MyColors.dangerPieceBorderDefault, 3));
            }
            else
                Game.blocks[move[0]][move[1]].getLabel().setBorder(Utilities.applyBorder(MyColors.possibleMoveBorderBlue, 3));
        }
    }

    boolean isSafeMove(Block clickedBlock, int endRow, int endCol){
        Piece startPiece = clickedBlock.getPiece();
        Piece endPiece = Game.blocks[endRow][endCol].getPiece();
        Game.blocks[endRow][endCol].setPiece(startPiece);
        clickedBlock.setPiece(null);
        boolean isKingCheck = GameOver.isKingInCheck(Game.blocks[endRow][endCol].getPiece().isBlack);
        Game.blocks[endRow][endCol].setPiece(endPiece);
        clickedBlock.setPiece(startPiece);

        return !isKingCheck;
    }

//    Piece promptForPromotionChoice(boolean isBlack) {
//        Object[] options = { "Queen", "Rook", "Bishop", "Knight" };
//        int choice = JOptionPane.showOptionDialog(null, "Select a piece to promote your pawn:", "Promotion", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, 0);
//
//        if (choice == 0) {
//            return new Piece(PieceType.queen, isBlack,32);
//        } else if (choice == 1) {
//            return new Piece(PieceType.rook, isBlack,32);
//        } else if (choice == 2) {
//            return new Piece(PieceType.bishop, isBlack,32);
//        } else {
//            return new Piece(PieceType.knight, isBlack,32);
//        }
//    }

    void movePiece(int toRow, int toCol){
        Block selectedBlock = Game.blocks[selectedRow][selectedCol];
        Block destinationBlock = Game.blocks[toRow][toCol];
        destinationBlock.setPiece(selectedBlock.getPiece());
        selectedBlock.setPiece(null);
//        if (destinationBlock.getPiece().type == PieceType.PAWN && ((toRow == 0) || (toRow == 7))) {
//            Piece promotionPiece = promptForPromotionChoice(destinationBlock.getPiece().isBlack);
//            destinationBlock.setPiece(promotionPiece);
//        }
//        else
            GameSounds.movePieceSound();
        Game.chessBoard.isBlackTurn = !Game.chessBoard.isBlackTurn;
        Game.stage.updateStage();

        if(GameOver.isCheckMate()){
            GameSounds.gameEndSound();
//            String winner = Game.chessBoard.isBlackTurn? "White":"Black";
//            String responses[] = {"Play Again", "Close"};
//            int answer = JOptionPane.showOptionDialog(null, winner + " wins the game", "Check Mate", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, responses, 0);
//            if(answer == 0){
//                Game.chessBoard.restartGame();
//            }
//            else
//                Game.frame.dispose();
        }
    }

    ArrayList<int[]> calculateRookMoves(Block block){
        int directions[][] = {{1,0}, {-1,0}, {0, 1}, {0, -1}};
        return Utilities.calculateMovesUtilityWithLoop(directions, block);
    }
    ArrayList<int[]> calculateBishopMoves(Block block){
        int directions[][] = {{1,1}, {1,-1}, {-1, 1}, {-1, -1}};
        return Utilities.calculateMovesUtilityWithLoop(directions, block);
    }
    ArrayList<int[]> calculateQueenMoves(Block block){
        int directions[][] = {{1,1}, {1,-1}, {-1,1}, {-1,-1}, {1,0}, {-1, 0}, {0, 1}, {0, -1}};
        return Utilities.calculateMovesUtilityWithLoop(directions, block);
    }
    ArrayList<int[]> calculateKnightMoves(Block block){
        int directions[][] = {{2,1}, {2,-1}, {-1,2}, {1,2}, {-2,1}, {-2, -1}, {1, -2}, {-1, -2}};
        return Utilities.calculateMovesUtilityWithoutLoop(directions, block);
    }
    ArrayList<int[]> calculateKingMoves(Block block){
        int directions[][] = {{1,0}, {1,1}, {1,-1}, {0,1}, {0,-1}, {-1, 0}, {-1, 1}, {-1, -1}};
        return Utilities.calculateMovesUtilityWithoutLoop(directions, block);
    }
    ArrayList<int[]> calculatePawnMoves(Block block){

        // Checking if clicked piece is white or black
        int pieceDirection = -1;
        if(block.getPiece().isBlack)
            pieceDirection = 1;

        // Check if pawn can move one step ahead;
        int oneStepInX = 0;
        if(Utilities.isInBoard(block.getRow() + pieceDirection, block.getCol()))
            if(Game.blocks[block.getRow() + pieceDirection][block.getCol()].getPiece() == null)
                oneStepInX = 1;

        // Check if pawn can move 2 steps ahead:
        int twoStepsInX = 0;
        if((block.getRow() == 1 && block.getPiece().isBlack == true) || (block.getRow() == 6 && block.getPiece().isBlack == false))
            if(Utilities.isInBoard(block.getRow() + 2*pieceDirection, block.getCol()))
                if(Game.blocks[block.getRow() + 2*pieceDirection][block.getCol()].getPiece() == null && oneStepInX == 1)
                    twoStepsInX = 2;


        // Check if can be kill on Diagnols:
        int rightDiagnolX = 0;
        int rightDiagnolY = 0;
        int leftDiagnolX = 0;
        int leftDiagnolY = 0;

        if(Utilities.isInBoard(block.getRow()+1*pieceDirection, block.getCol()+1)){
            Piece pieceAtRightDiagnol = Game.blocks[block.getRow() + 1 * pieceDirection][block.getCol() + 1].getPiece();
            if(pieceAtRightDiagnol != null){
                if(pieceAtRightDiagnol.isBlack !=  block.getPiece().isBlack){
                    rightDiagnolX = pieceDirection;
                    rightDiagnolY = 1;
                }
            }
        }
        if(Utilities.isInBoard(block.getRow()+1*pieceDirection, block.getCol()-1)){
            Piece pieceAtLeftDiagnol = Game.blocks[block.getRow() + 1 * pieceDirection][block.getCol() - 1].getPiece();
            if(pieceAtLeftDiagnol != null){
                if(pieceAtLeftDiagnol.isBlack != block.getPiece().isBlack){
                    leftDiagnolX = pieceDirection;
                    leftDiagnolY = -1;
                }
            }
        }

        int directions[][] = {{twoStepsInX*pieceDirection,0}, {oneStepInX*pieceDirection, 0}, {rightDiagnolX,rightDiagnolY}, {leftDiagnolX,leftDiagnolY}};
        return Utilities.calculateMovesUtilityWithoutLoop(directions, block);
    }
}
