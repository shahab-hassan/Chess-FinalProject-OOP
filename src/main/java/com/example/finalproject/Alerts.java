package com.example.finalproject;

import javafx.scene.control.*;

import java.net.MalformedURLException;

public class Alerts {
    static void saveGamePrompt(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("UnSaved Changes");
        alert.setHeaderText("You have unsaved changes... Want to save?");
        alert.setContentText("Press 'save' to save the game");
        alert.initOwner(Game.primaryStage);
        ButtonType yes = new ButtonType("Save");
        ButtonType no = new ButtonType("No");
        alert.getButtonTypes().setAll(yes, no);
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == yes) {
                try {
                    ChessStage.chessBtns.saveGameBtnAction();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    static void showGameOverAlert(String title, String header){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText("Click rematch to play again!");
        alert.initOwner(Game.primaryStage);
        ButtonType yes = new ButtonType("Rematch");
        ButtonType no = new ButtonType("No");
        alert.getButtonTypes().setAll(yes, no);
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == yes)
                Game.myStage.startMainGame();
        });
    }
    static void mainMenuPrompt(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Main Menu");
        alert.setHeaderText("Go to main menu?");
        alert.setContentText("Press 'Leave' to move to main menu");
        alert.initOwner(Game.primaryStage);
        ButtonType yes = new ButtonType("Leave");
        ButtonType no = new ButtonType("No");
        alert.getButtonTypes().setAll(yes, no);
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == yes) {
                try {
                    Game.primaryStage.setScene(Game.splashScreen.logoScene());
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    static void noGameSavedPrompt(CheckBox humanCB, ComboBox<String> gameDurations){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("No Game saved");
        alert.setHeaderText("You don't have any saved games...");
        alert.setContentText("Press 'Start new' to play new game");
        alert.initOwner(Game.primaryStage);
        ButtonType yes = new ButtonType("Start New");
        ButtonType no = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(yes, no);
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == yes) {
                try {
                    if(humanCB.isSelected())
                        Game.chessBoard.ai = false;
                    else
                        Game.chessBoard.ai = true;
                    int minutes = Integer.parseInt(gameDurations.getSelectionModel().getSelectedItem().split(" ")[0]);
                    ChessStage.clock.originalMinutes = minutes;
                    Game.myStage.startMainGame();
                    Game.primaryStage.setScene(Game.scene);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    static Piece promotionChoiceAlert(boolean isBlack){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Promotion");
        alert.setHeaderText("Select a piece to promote your pawn:");
        alert.setContentText(null);
        alert.initOwner(Game.primaryStage);
        ButtonType queen = new ButtonType("Queen");
        ButtonType rook = new ButtonType("Rook");
        ButtonType bishop = new ButtonType("Bishop");
        ButtonType knight = new ButtonType("Knight");
        alert.getButtonTypes().setAll(queen, rook, bishop, knight);
        Piece piece = new Piece(PieceType.QUEEN, isBlack, 32);
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == queen)
                piece.setType(PieceType.QUEEN);
            else if (buttonType == rook)
                piece.setType(PieceType.ROOK);
            else if (buttonType == bishop)
                piece.setType(PieceType.BISHOP);
            else
                piece.setType(PieceType.KNIGHT);
        });
        piece.updatePiece();
        return piece;
    }
}
