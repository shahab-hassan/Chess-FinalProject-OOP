package com.example.finalproject;

public class HardStockfish extends Stockfish{

        @Override
        public String getBestMove(String fen) {
            sendCommand("position fen " + fen);
            sendCommand("go movetime 500");
            String response = readResponse();
            return extractBestMove(response);
        }
}

