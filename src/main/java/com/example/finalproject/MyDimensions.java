package com.example.finalproject;

public class MyDimensions{
    // Frame:
    static double frameWidth = 1200;
    static double frameHeight = 750;
    // Board:
    static double boardWidth = frameWidth/1.5;
    static double boardHeight = frameHeight;
    static double boardX = 0;
    static double boardY = 0;
    // Clock:
    static double clockWidth = 120;
    static double clockHeight = 45;
    static double blackClockX = boardWidth + 30;
    static double blackClockY =  30;
    static double whiteClockX = boardWidth + 30;
    static double whiteClockY = frameHeight - 30 - clockHeight;
    // Kills:
    static double blackKillsPanelHeight = 40;
    static double blackKillsPanelWidth = 340;
    static double whiteKillsPanelHeight = 40;
    static double whiteKillsPanelWidth = 340;
    static double whiteKillsPanelX = boardWidth+30;
    static double whiteKillsPanelY = whiteClockY - whiteKillsPanelHeight-10;
    static double blackKillsPanelX = whiteKillsPanelX;
    static double blackKillsPanelY = blackClockY + blackKillsPanelHeight + 20;
    // Themes:
    static double themeContainerX = boardWidth+30;
    static double themeContainerY = frameHeight-215;
    static double themeContainerWidth = Double.MAX_VALUE;
    static double themeContainerHeight = 20;
    // Game Buttons:
    static double chessBtnsPaneX = boardWidth + 30;
    static double chessBtnsPaneY = clockHeight + blackKillsPanelHeight + 60;
    static double chessBtnsPaneWidth = 340;
    static double chessBtnsPaneHeight = 300;
    static double undoRedoPaneHeight = clockHeight;
    static double undoRedoPaneWidth = clockWidth + 60;
    static double undoRedoPaneX = blackClockX + clockWidth + 30;
    static double undoRedoPaneY = blackClockY;
}