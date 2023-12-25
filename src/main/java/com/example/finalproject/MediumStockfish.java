package com.example.finalproject;

public class MediumStockfish extends Stockfish {

        @Override
        public String getBestMove(String fen) {
            sendCommand("position fen " + fen);
            sendCommand("go movetime 150");
            String response = readResponse();
            return extractBestMove(response);
        }
}
