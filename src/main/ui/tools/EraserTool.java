package ui.tools;

import model.NotePanel;
import model.Pixel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class EraserTool implements MouseMotionListener {
    private final NotePanel notePanel;
    private boolean active;

    // CONSTRUCTOR
    // EFFECTS: creates a new EraserTool
    public EraserTool(NotePanel notePanel) {
        this.notePanel = notePanel;

        notePanel.addMouseMotionListener(this);
    }

    // EFFECTS: toggles whether EraserTool is active or not
    public void setActive(Boolean b) {
        active = b;
    }

    // MODIFIES: this
    // EFFECTS: when mouse is dragged with button held down, and if the pen tool is active:
    //          removes pixels from NotePanel
    @Override
    public void mouseDragged(MouseEvent e) {
        if (active) {
            notePanel.removePixel(new Pixel(e.getX(), e.getY(), Color.BLACK));
            notePanel.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}

