package ui.tools;

import model.NotePanel;
import model.NotePanelData;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

// TODO: add a colour feature to textTool
public class TextTool implements Tool {

    private NotePanel textPane;
    private Color colour;
    private boolean running;

    public TextTool(NotePanel textPane, Color c) {
        this.textPane = textPane;
        colour = c;
        textPane.setColour(colour);
    }

    public void setActive(Boolean b) {
        if (b) {
            textPane.toggleEditable(true);
        } else {
            textPane.toggleEditable(false);
        }
    }
}
