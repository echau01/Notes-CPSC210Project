package ui.tools;

import model.NotePanel;
import model.Pixel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class EraserTool implements MouseMotionListener {
    private NotePanel notePanel;

    private int x;
    private int y;
    private boolean active;

    public EraserTool(NotePanel notePanel) {
        this.notePanel = notePanel;

        notePanel.addMouseMotionListener(this);
    }

    public void setActive(Boolean b) {
        active = b;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (active) {
            x = e.getX();
            y = e.getY();
            notePanel.removePixel(new Pixel(x, y, Color.BLACK));
            notePanel.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
            ;
    }
}

