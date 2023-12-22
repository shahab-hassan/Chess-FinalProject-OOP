package com.example.finalproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Stockfish {

    private Process stockfishProcess;
    private BufferedReader stockfishInput;
    private OutputStream stockfishOutput;

    public Stockfish() {
        initializeStockfishProcess();
    }

    private void initializeStockfishProcess() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("src/stockfish/stockfish-windows-x86-64-avx2.exe");
            stockfishProcess = processBuilder.start();
            stockfishInput = new BufferedReader(new InputStreamReader(stockfishProcess.getInputStream()));
            stockfishOutput = stockfishProcess.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getBestMove(String fen) {
        sendCommand("position fen " + fen);
        sendCommand("go movetime 400");
        String response = readResponse();
        return extractBestMove(response);
    }

    private String extractBestMove(String response) {
        String[] lines = response.split("\n");
        for (String line : lines) {
            if (line.startsWith("bestmove")) {
                return line.split(" ")[1];
            }
        }
        return null;
    }

    void sendCommand(String command) {
        try {
            stockfishOutput.write((command + "\n").getBytes());
            stockfishOutput.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String readResponse() {
        List<String> lines = new ArrayList<>();
        try {
            String line;
            while ((line = stockfishInput.readLine()) != null) {
                lines.add(line);
                if (line.startsWith("bestmove")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.join("\n", lines);
    }

    int[] convertMoveToRowColumn(String move) {
        if (move.length() != 4) {
            return null;
        }

        char fromFile = move.charAt(0);
        char fromRank = move.charAt(1);
        char toFile = move.charAt(2);
        char toRank = move.charAt(3);

        int fromCol = fromFile - 'a';
        int fromRow = '8' - fromRank;
        int toCol = toFile - 'a';
        int toRow = '8' - toRank;

        return new int[]{fromRow, fromCol, toRow, toCol};
    }

    public static void aiSounds(int toRow, int toCol) {
        if (Game.blocks[toRow][toCol].getPiece() == null) {
            Sounds.movePieceSound();
        } else {
            Sounds.captureSound();
        }
    }
}


//package com.example.finalproject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//class Stockfish {
//
//    private Process stockfishProcess;
//    private BufferedReader stockfishInput;
//    private OutputStream stockfishOutput;
//
//    Stockfish() {
//        initializeStockfishProcess();
//    }
//
//    private void initializeStockfishProcess() {
//        try {
//            ProcessBuilder processBuilder = new ProcessBuilder("src/stockfish/stockfish-windows-x86-64-avx2.exe");
//            stockfishProcess = processBuilder.start();
//            stockfishInput = new BufferedReader(new InputStreamReader(stockfishProcess.getInputStream()));
//            stockfishOutput = stockfishProcess.getOutputStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    String getBestMove(String fen) {
//        sendCommand("position fen " + fen);
//        sendCommand("go movetime 400");
//        String response = readResponse();
//        return extractBestMove(response);
//    }
//
//    private String extractBestMove(String response) {
//        String[] lines = response.split("\n");
//        for (String line : lines) {
//            if (line.startsWith("bestmove")) {
//                return line.split(" ")[1];
//            }
//        }
//        return null;
//    }
//    private void sendCommand(String command) {
//        try {
//            stockfishOutput.write((command + "\n").getBytes());
//            stockfishOutput.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    private String readResponse() {
//        List<String> lines = new ArrayList<>();
//        try {
//            String line;
//            while ((line = stockfishInput.readLine()) != null) {
//                lines.add(line);
//                if (line.startsWith("bestmove")) {
//                    break;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return String.join("\n", lines);
//    }
//
//    int[] convertMoveToRowColumn(String move) {
//        if (move.length() != 4) {
//            return null;
//        }
//
//        char fromFile = move.charAt(0);
//        char fromRank = move.charAt(1);
//        char toFile = move.charAt(2);
//        char toRank = move.charAt(3);
//
//        int fromCol = fromFile - 'a';
//        int fromRow = '8' - fromRank;
//        int toCol = toFile - 'a';
//        int toRow = '8' - toRank;
//
//        return new int[]{fromRow, fromCol, toRow, toCol};
//    }
//    void aiSounds(int toRow, int toCol) {
//        if (Game.blocks[toRow][toCol].getPiece() == null)
//            Sounds.movePieceSound();
//        else {
//            Sounds.captureSound();
//            ChessStage.kills.whiteKilledPieces[ChessStage.kills.whiteKillsCount++] = Game.blocks[toRow][toCol].getPiece();
//        }
//    }
//}
