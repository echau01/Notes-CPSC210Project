package ui.tools;

import model.NotePanel;
import model.NotePanelData;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class TextTool implements Tool {

    private NotePanel textPane;
    private Color colour;
    private boolean running;

    public TextTool(NotePanel textPane, Color c) {
        this.textPane = textPane;
        colour = c;
        textPane.setColour(colour);
        textPane.toggleEditable(true);
    }

    @Override
    public void setRunning(Boolean b) {
        running = b;
    }
}
