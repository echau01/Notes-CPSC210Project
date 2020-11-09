package ui.tools;

import model.Notes;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class TextTool {

    private JTextPane textPane;
    private Color colour;
    private Notes note;

    public TextTool(Notes note, JTextPane textPane, Color c) {
        this.textPane = textPane;
        this.note = note;
        colour = c;
        setStyle();
        textPane.setEditable(true);
    }

    private void setStyle() {
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setForeground(attributeSet, colour);
        textPane.setCharacterAttributes(attributeSet, true);

        test();
    }

    private void test() {
        String e = textPane.getText();
        System.out.println(e);

    }
}
