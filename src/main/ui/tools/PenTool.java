package ui.tools;

import model.Notes;
import model.Pixel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

// https://stackoverflow.com/questions/23122492/java-jframe-draw
public class PenTool extends JPanel implements MouseMotionListener {
    private Color colour;
    private JTextPane notePanel;
    private Notes note;

    private int x;
    private int y;

    private final LinkedList<Pixel> pixels;

    public PenTool(Notes note, JTextPane notePanel, Color colour) {
        this.note = note;
        this.notePanel = notePanel;
        this.colour = colour;
        pixels = new LinkedList<>();

        notePanel.addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        pixels.stream().forEach((p) -> p.draw(g));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        pixels.add(new Pixel(x, y, colour));
        System.out.println(pixels);
        notePanel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        ;
    }
}
