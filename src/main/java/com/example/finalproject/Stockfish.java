package com.example.finalproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class Stockfish  implements BestMoveProcessing{

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

    @Override
   public abstract String getBestMove(String fen) ;

    String extractBestMove(String response) {
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
    //new method
    public String evaluatePosition(String fen) {
        sendCommand("position fen " + fen);
        sendCommand("eval");
        String response = readEvaluationResponse();
        return extractEvaluationScore(response);
    }
    //new method
    public double convertPositionToInt()
    {
        double number=0;
        String s= evaluatePosition(FenConverter.boardToFEN(Game.blocks));
        if(s.startsWith("+"))
        {
            String n=s.substring(1);
            number=Double.parseDouble(n);
        }
        if(s.startsWith("-"))
        {
            String n=s.substring(1);
            number=-Double.parseDouble(n);
        }
        return number;

    }
    //new method
    private String extractEvaluationScore(String response) {
        String[] lines = response.split("\n");
        for (String line : lines) {
            if (line.startsWith("Classical evaluation")) {
                String[] tokens = line.split(" ");
                for (int i = 0; i < tokens.length; i++) {
                    if(tokens[i].startsWith("+")|| tokens[i].startsWith("-"))
                    {
                        return tokens[i];

                    }


                }
            }
        }
        return "-20.0";
    }
    //new method
    public String readEvaluationResponse() {
        List<String> lines = new ArrayList<>();
        try {
            String line;
            while ((line = stockfishInput.readLine()) != null) {
                lines.add(line);
                if (line.startsWith("Final evaluation")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.join("\n", lines);
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
            ChessStage.kills.whiteKilledPieces[ChessStage.kills.whiteKillsCount++] = Game.blocks[toRow][toCol].getPiece();
        }
    }
}
