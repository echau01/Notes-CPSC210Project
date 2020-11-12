package ui.tools;

import model.NotePanel;
import model.NotePanelData;
import model.Pixel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


// https://stackoverflow.com/questions/23122492/java-jframe-draw
public class PenTool implements Tool, MouseMotionListener {
    private Color colour;
    private NotePanel notePanel;

    private int x;
    private int y;
    private boolean active;

    public PenTool(NotePanel notePanel, Color colour) {
        this.notePanel = notePanel;
        this.colour = colour;

        notePanel.addMouseMotionListener(this);
    }

    public void setActive(Boolean b) {
        active = b;
    }

    public void setColour(Color c) {
        colour = c;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (active) {
            x = e.getX();
            y = e.getY();
            notePanel.addPixel(new Pixel(x, y, colour));
            notePanel.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        ;
    }
}
