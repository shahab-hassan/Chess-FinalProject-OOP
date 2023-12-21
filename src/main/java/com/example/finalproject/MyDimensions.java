package com.example.finalproject;

public class MyDimensions{
    static double frameWidth = 1200;
    static double frameHeight = 750;
    static double boardWidth = frameWidth/1.5;
    static double boardHeight = frameHeight;
    static double boardX = 0;
    static double boardY = 0;
    static double whiteKillsPanelX = 0;
    static double whiteKillsPanelY = 10;
    static double whiteKillsPanelWidth = boardWidth;
    static double whiteKillsPanelHeight = 40;
    static double blackKillsPanelX = 0;
    static double blackKillsPanelY = frameHeight-90;
    static double blackKillsPanelWidth = boardWidth;
    static double blackKillsPanelHeight = 40;

    static double blackClockX = boardWidth - 100;
    static double blackClockY = boardY - 60;
    static double whiteClockX = boardWidth - 100;
    static double whiteClockY = frameHeight-150;

    static double clockWidth = 80;
    static double clockHeight = 40;
    static double themeContainerX = boardWidth+25;
    static double themeContainerY = frameHeight-100;
    static double themeContainerWidth = Double.MAX_VALUE;
    static double themeContainerHeight = 100;
}