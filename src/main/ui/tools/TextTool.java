package ui.tools;

import model.NotePanel;
import model.Notes;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class TextTool {

    private NotePanel textPane;
    private Color colour;
    private Notes note;

    public TextTool(Notes note, NotePanel textPane, Color c) {
        this.textPane = textPane;
        this.note = note;
        colour = c;
        textPane.setColour(colour);
        textPane.toggleEditable(true);
    }
}
