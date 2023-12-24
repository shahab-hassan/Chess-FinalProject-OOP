package com.example.finalproject;

import javafx.concurrent.Task;

public class StockfishWorker extends Task<String> {

    @Override
    protected String call() {
        String s = FenConverter.boardToFEN(Game.blocks);
        return Game.stockfish.getBestMove(s);
    }

    @Override
    protected void succeeded() {
        try {
            String bestMove = get();
            int[] coordinates = Game.stockfish.convertMoveToRowColumn(bestMove);
            assert coordinates != null;
            Stockfish.aiSounds(coordinates[2], coordinates[3]);
            Move.selectedRow = coordinates[0];
            Move.selectedCol = coordinates[1];
            Game.move.movePiece(coordinates[2], coordinates[3]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}