package com.example.finalproject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


public class EasyStockfish extends Stockfish {
    @Override
    public String getBestMove(String fen) {
        sendCommand("position fen " + fen);
        sendCommand("go movetime 5");
        String response = readResponse();
        return extractBestMove(response);
    }
}
