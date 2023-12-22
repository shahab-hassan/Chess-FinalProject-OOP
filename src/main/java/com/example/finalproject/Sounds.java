package com.example.finalproject;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sounds {

    private static void playSound(String soundPath) {
        try {
            File file = new File(soundPath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    static void captureSound() {
        playSound("src/sounds/capture.wav");
    }

    static void movePieceSound() {
        playSound("src/sounds/move-self.wav");
    }

    static void castleSound() {
        playSound("src/sounds/castle.wav");
    }

    static void gameStartSound() {
        playSound("src/sounds/game-start.wav");
    }

    static void gameEndSound() {
        playSound("src/sounds/game-end.wav");
    }

    static void tensecondsSound() {
        playSound("src/sounds/tenseconds.wav");
    }

    static void promoteSound() {
        playSound("src/sounds/promote.wav");
    }

    static void moveCheckSound() {
        playSound("src/sounds/move-check.wav");
    }
}
