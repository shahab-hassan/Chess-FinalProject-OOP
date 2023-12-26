package com.example.finalproject;

import javafx.animation.PauseTransition;
import javafx.concurrent.Task;
import javafx.util.Duration;

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
            Move.selectedRow = coordinates[0];
            Move.selectedCol = coordinates[1];
            Stockfish.aiSounds(coordinates[2], coordinates[3]);
            Game.move.movePiece(coordinates[2], coordinates[3]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}