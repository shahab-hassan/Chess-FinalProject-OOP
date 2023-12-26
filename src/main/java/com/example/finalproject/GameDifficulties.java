package com.example.finalproject;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

class EasyMode extends Stockfish {
    @Override
    public String getBestMove(String fen) {
        PauseTransition pause = new PauseTransition(Duration.millis(500));
        pause.setOnFinished(event -> {
            sendCommand("setoption name Skill level value 1");
            sendCommand("position fen " + fen);
            sendCommand("go depth 1");
        });
        pause.play();
        String response = readResponse();
        return extractBestMove(response);
    }
}
class MediumMode extends Stockfish {
    @Override
    public String getBestMove(String fen) {
        PauseTransition pause = new PauseTransition(Duration.millis(500));
        pause.setOnFinished(event -> {
            sendCommand("setoption name Skill level value 5");
            sendCommand("position fen " + fen);
            sendCommand("go depth 1");
        });
        pause.play();
        String response = readResponse();
        return extractBestMove(response);
    }
}
class HardMode extends Stockfish{

    @Override
    public String getBestMove(String fen) {
        PauseTransition pause = new PauseTransition(Duration.millis(500));
        pause.setOnFinished(event -> {
            sendCommand("setoption name Skill level value 20");
            sendCommand("position fen " + fen);
            sendCommand("go depth 1");
        });
        pause.play();
        String response = readResponse();
        return extractBestMove(response);
    }
}

