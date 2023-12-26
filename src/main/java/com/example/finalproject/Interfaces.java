package com.example.finalproject;

interface BestMoveProcessing {
    String getBestMove(String fen);
}
interface movePieceMethod{
    void movePiece(int row, int col);
}

interface specialMovesMethods{
    void showMoves();
    boolean isPossible();
}