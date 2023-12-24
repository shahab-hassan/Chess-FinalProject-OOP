package com.example.finalproject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveGame {
    static boolean isGameSaved = true;
    static void saveCurrentGame(){
        SaveGame.isGameSaved = true;
        try{
            FileOutputStream obj = new FileOutputStream("savedGameObj.txt");
            ObjectOutputStream oos = new ObjectOutputStream(obj);
            for(Block blockArr[]: Game.blocks){
                for(Block block: blockArr){
                    if(block.getPiece() != null)
                        oos.writeObject(new SaveGameObj(new PieceObj(block.getPiece().type, block.getPiece().isBlack), block.getRow(), block.getCol()));
                    else
                        oos.writeObject(new SaveGameObj(null, block.getRow(), block.getCol()));
                }
            }
            for(Piece piece: ChessStage.kills.blackKilledPieces){
                if(piece!=null)
                    oos.writeObject(new PieceObj(piece.type, piece.isBlack));
            }
            for(Piece piece: ChessStage.kills.whiteKilledPieces){
                if(piece!=null)
                    oos.writeObject(new PieceObj(piece.type, piece.isBlack));
            }
            FileOutputStream data = new FileOutputStream("savedGameData.txt");
            PrintStream ps = new PrintStream(data);
            ps.println(Game.chessBoard.isBlackTurn);
            ps.println(Game.chessBoard.ai);
            ps.println(ChessStage.clock.elapsedTime1);
            ps.println(ChessStage.clock.elapsedTime2);
            ps.println(ChessStage.kills.blackKillsCount);
            ps.println(ChessStage.kills.whiteKillsCount);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    static void loadSavedGame(){
        File file = new File("savedGameObj.txt");
        ArrayList<PieceObj> blackKills = new ArrayList<>();
        ArrayList<PieceObj> whiteKills = new ArrayList<>();
        if(file.exists() && file.length()>0){
            Game.myStage.startMainGame();
            try{
                FileInputStream fis = new FileInputStream("savedGameObj.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);
                int count = 0;
                while (true){
                    try{
                        if(count<64){
                            SaveGameObj obj = (SaveGameObj) ois.readObject();
                            if(obj.pieceObj == null)
                                Game.blocks[obj.row][obj.col].setPiece(null);
                            else
                                Game.blocks[obj.row][obj.col].setPiece(new Piece(obj.pieceObj.pieceType, obj.pieceObj.isBlack, 32));
                        }
                        else{
                            PieceObj obj = (PieceObj) ois.readObject();
                            if(obj.isBlack)
                                blackKills.add(obj);
                            else
                                whiteKills.add(obj);
                        }
                        count++;
                    }
                    catch (EOFException e){
                        break;
                    }
                }
                File data = new File("savedGameData.txt");
                Scanner sc = new Scanner(data);
                Game.chessBoard.isBlackTurn = sc.nextBoolean();
                Game.chessBoard.ai = sc.nextBoolean();
                ChessStage.clock.elapsedTime1 = sc.nextInt();
                ChessStage.clock.elapsedTime2 = sc.nextInt();
                ChessStage.clock.setHoursMinutesSeconds();
                ChessStage.kills.blackKillsCount = sc.nextInt();
                ChessStage.kills.whiteKillsCount = sc.nextInt();
                for(int i=0; i<ChessStage.kills.blackKillsCount; i++){
                    PieceObj obj = blackKills.get(i);
                    ChessStage.kills.blackKilledPieces[i] = new Piece(obj.pieceType, obj.isBlack, 32);
                }
                for(int i=0; i<ChessStage.kills.whiteKillsCount; i++){
                    PieceObj obj = whiteKills.get(i);
                    ChessStage.kills.whiteKilledPieces[i] = new Piece(obj.pieceType, obj.isBlack, 32);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            Game.myStage.updateStage();
            Game.primaryStage.setScene(Game.scene);
        }
    }
    static boolean isSavedGameAvailable(){
        File file = new File("savedGameObj.txt");
        return file.exists() && file.length() > 0;
    }
}
