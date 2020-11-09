package ui.tools;

import model.NotePanel;
import model.Notes;
import model.Pixel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


// https://stackoverflow.com/questions/23122492/java-jframe-draw
public class PenTool implements MouseMotionListener {
    private Color colour;
    private NotePanel notePanel;
    private Notes note;

    private int x;
    private int y;

    public PenTool(Notes note, NotePanel notePanel, Color colour) {
        this.note = note;
        this.notePanel = notePanel;
        this.colour = colour;

        notePanel.addMouseMotionListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        notePanel.addPixel(new Pixel(x, y, colour));
        notePanel.repaint();
        System.out.println(x + y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        ;
    }
}
