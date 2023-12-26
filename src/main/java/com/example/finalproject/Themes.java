package com.example.finalproject;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Themes implements EventHandler<MouseEvent> {
    FlowPane themesPanel;
    private Circle grayBtn, blueBtn, redBtn, greenBtn;
    Themes(){
        themesPanel = new FlowPane(15, 0);
        themesPanel.setLayoutX(MyDimensions.themeContainerX);
        themesPanel.setLayoutY(MyDimensions.themeContainerY);
        themesPanel.setPrefSize(MyDimensions.themeContainerWidth, MyDimensions.themeContainerHeight);
        grayBtn = initializeBtn(Color.DARKGRAY);
        greenBtn = initializeBtn(Color.GREEN);
        blueBtn = initializeBtn(Color.BLUE);
        redBtn = initializeBtn(Color.RED);
    }
    Circle initializeBtn(Color color){
        Circle btn = new Circle();
        btn.setRadius(35);
        btn.setFill(color);
        btn.setOnMouseClicked(this);
        themesPanel.getChildren().add(btn);
        return btn;
    }
    @Override
    public void handle(MouseEvent e){
        GameAnalysis.progressBar.getStyleClass().clear();
        if(e.getSource() == grayBtn){
            applyGrayTheme();
            Game.myStage.updateStage();
        }
        else if(e.getSource() == greenBtn){
            applyGreenTheme();
            Game.myStage.updateStage();
        }
        else if(e.getSource() == blueBtn){
            applyBlueTheme();
            Game.myStage.updateStage();
        }
        else{
            applyRedTheme();
            Game.myStage.updateStage();
        }
        ChessStage.clock.getBlackClock().setBackground(Utilities.applyBackground(MyColors.clockBgDefault));
        ChessStage.clock.getWhiteClock().setBackground(Utilities.applyBackground(MyColors.clockBgDefault));

        String gradientBg = getGradientColor(MyColors.darkBlockDefault, MyColors.darkBlockOnHoverDefault, MyColors.lightBlockDefault, MyColors.lightBlockOnHoverDefault);
        ChessStage.chessBtns.restartBtn.setStyle("-fx-background-color: "+gradientBg+"; -fx-text-fill: #000000;");
        ChessStage.chessBtns.mainMenuBtn.setStyle("-fx-background-color: "+gradientBg+"; -fx-text-fill: #000000;");
        ChessStage.chessBtns.saveGameBtn.setStyle("-fx-background-color: "+gradientBg+"; -fx-text-fill: #000000;");
    }
    void applyGrayTheme(){
        MyColors.darkBlockDefault = MyColors.darkBlockGray;
        MyColors.lightBlockDefault = MyColors.lightBlockGray;
        MyColors.lightBlockOnHoverDefault = MyColors.lightBlockOnHoverGray;
        MyColors.darkBlockOnHoverDefault = MyColors.darkBlockOnHoverGray;
        MyColors.clockBgDefault = MyColors.clockBgGray;
        MyColors.possibleMoveBorderDefault = MyColors.possibleMoveBorderGray;
        MyColors.dangerPieceBorderDefault = MyColors.dangerPieceBorderGray;
        Game.scene.setFill(MyColors.darkBlockGray);
        GameAnalysis.progressBar.getStyleClass().add("progress-bar2");
    }
    void applyGreenTheme(){
        MyColors.darkBlockDefault = MyColors.darkBlockGreen;
        MyColors.lightBlockDefault = MyColors.lightBlockGreen;
        MyColors.lightBlockOnHoverDefault = MyColors.lightBlockOnHoverGreen;
        MyColors.darkBlockOnHoverDefault = MyColors.darkBlockOnHoverGreen;
        MyColors.clockBgDefault = MyColors.clockBgGreen;
        MyColors.possibleMoveBorderDefault = MyColors.possibleMoveBorderGreen;
        MyColors.dangerPieceBorderDefault = MyColors.dangerPieceBorderGreen;
        Game.scene.setFill(MyColors.darkBlockGreen);
        GameAnalysis.progressBar.getStyleClass().add("progress-bar1");
    }
    void applyBlueTheme(){
        MyColors.darkBlockDefault = MyColors.darkBlockBlue;
        MyColors.lightBlockDefault = MyColors.lightBlockBlue;
        MyColors.lightBlockOnHoverDefault = MyColors.lightBlockOnHoverBlue;
        MyColors.darkBlockOnHoverDefault = MyColors.darkBlockOnHoverBlue;
        MyColors.clockBgDefault = MyColors.clockBgBlue;
        MyColors.possibleMoveBorderDefault = MyColors.possibleMoveBorderBlue;
        MyColors.dangerPieceBorderDefault = MyColors.dangerPieceBorderBlue;
        Game.scene.setFill(MyColors.darkBlockBlue);
        GameAnalysis.progressBar.getStyleClass().add("progress-bar4");
    }
    void applyRedTheme(){
        MyColors.darkBlockDefault = MyColors.darkBlockRed;
        MyColors.lightBlockDefault = MyColors.lightBlockRed;
        MyColors.lightBlockOnHoverDefault = MyColors.lightBlockOnHoverRed;
        MyColors.darkBlockOnHoverDefault = MyColors.darkBlockOnHoverRed;
        MyColors.clockBgDefault = MyColors.clockBgRed;
        MyColors.possibleMoveBorderDefault = MyColors.possibleMoveBorderRed;
        MyColors.dangerPieceBorderDefault = MyColors.dangerPieceBorderRed;
        Game.scene.setFill(MyColors.darkBlockRed);
        GameAnalysis.progressBar.getStyleClass().add("progress-bar3");
    }
    String getGradientColor(Color dark, Color moreDark, Color light, Color moreLight){
        String darkStr = convertRGBToHexa(dark);
        String moreDarkStr = convertRGBToHexa(moreDark);
        String lightStr = convertRGBToHexa(light);
        String moreLightStr = convertRGBToHexa(moreLight);
        return "linear-gradient(from 0% 93% to 0% 100%, "+darkStr+" 0%, "+moreDarkStr+" 100%), "+moreDarkStr+", "+lightStr+", radial-gradient(center 50% 50%, radius 100%, "+lightStr+", "+moreLightStr+")";
    }
    String convertRGBToHexa(Color clr){
        return String.format("#%02X%02X%02X",
                (int) (clr.getRed() * 255),
                (int) (clr.getGreen() * 255),
                (int) (clr.getBlue() * 255));
    }
}