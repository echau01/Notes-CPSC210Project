package model;

import com.sun.xml.internal.ws.api.Component;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.util.LinkedList;

public class NotePanel extends JPanel {

    private JTextPane textPane;
    private LinkedList<Pixel> pixels;

    public NotePanel() {
        //setLayout(new BorderLayout());
        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setVisible(true);
        textPane.setOpaque(false);
        add(textPane);
        pixels = new LinkedList<>();
    }

    public void addPixel(Pixel p) {
        pixels.add(p);
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        pixels.stream().forEach((p) -> p.draw(g));
    }

    public void toggleEditable(boolean b) {
        textPane.setEditable(b);
    }

    public void setColour(Color colour) {
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setForeground(attributeSet, colour);
        textPane.setCharacterAttributes(attributeSet, true);
    }

}
