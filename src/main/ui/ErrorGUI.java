package ui;

import javax.swing.*;

// https://stackoverflow.com/questions/7080205/popup-message-boxes
public class ErrorGUI {
    public ErrorGUI(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage,
                "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
