package ui.tools;

import model.NotePanel;
import model.Pixel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class EraserTool implements Tool, MouseMotionListener {
    private NotePanel notePanel;

    private int x;
    private int y;
    private boolean running;

    public EraserTool(NotePanel notePanel) {
        this.notePanel = notePanel;

        notePanel.addMouseMotionListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        notePanel.removePixel(new Pixel(x, y, Color.BLACK));
        notePanel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
            ;
    }

    @Override
    public void setRunning(Boolean b) {
        running = b;
    }
}

