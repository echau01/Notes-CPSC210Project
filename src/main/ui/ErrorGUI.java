package ui;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

// https://stackoverflow.com/questions/7080205/popup-message-boxes
public class ErrorGUI {
    private static final String SOUND_URL = "./data/windows_10_error.wav";
    
    public ErrorGUI(String infoMessage, String titleBar) {
        playErrorSound();
        JOptionPane.showMessageDialog(null, infoMessage,
                "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);

    }

    // https://stackoverflow.com/questions/15526255/best-way-to-get-sound-on-button-press-for-a-java-calculator
    private void playErrorSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(SOUND_URL).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error when playing audio stream.");
            e.printStackTrace();
        }
    }
}
