package com.example.finalproject;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Utilities {
    static Background applyBackground(Color color){
        return new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
    }
    static Border applyBorder(Color color, int borderWidth){
        return new Border(new BorderStroke(color,BorderStrokeStyle.SOLID, CornerRadii.EMPTY,new BorderWidths(borderWidth)));
    }
    static boolean isInBoard(int r, int c){
        return r>=0 && r<8 && c>=0 && c<8;
    }
    static ArrayList<int[]> calculateMovesUtilityWithLoop(int directions[][], Block block){
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        for (int[] direction: directions){
            int i = block.getRow() + direction[0];
            int j = block.getCol() + direction[1];
            while(Utilities.isInBoard(i, j)){
                int move[] = new int[2];
                if(Game.blocks[i][j].getPiece() != null){
                    if(Game.blocks[i][j].getPiece().isBlack != Game.blocks[block.getRow()][block.getCol()].getPiece().isBlack){
                        move[0] = i;
                        move[1] = j;
                        possibleMoves.add(move);
                    }
                    break;
                }
                move[0] = i;
                move[1] = j;
                possibleMoves.add(move);
                i+=direction[0];
                j+=direction[1];
            }
        }
        return possibleMoves;
    }
    static ArrayList<int[]> calculateMovesUtilityWithoutLoop(int directions[][], Block block){
        ArrayList<int[]> possibleMoves = new ArrayList<int[]>();
        for (int[] direction: directions){
            int move[] = new int[2];
            int i = block.getRow() + direction[0];
            int j = block.getCol() + direction[1];
            if(Utilities.isInBoard(i, j)){
                if(Game.blocks[i][j].getPiece() != null){
                    if(Game.blocks[i][j].getPiece().isBlack != Game.blocks[block.getRow()][block.getCol()].getPiece().isBlack){
                        move[0] = i;
                        move[1] = j;
                        possibleMoves.add(move);
                    }
                }
                else{
                    move[0] = i;
                    move[1] = j;
                    possibleMoves.add(move);
                }
            }
        }
        return possibleMoves;
    }
}
