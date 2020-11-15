package model;

import model.exceptions.NoTitleException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.util.LinkedHashSet;

public class NotePanel extends JPanel {

    private JTextPane textPane;
    private String title;
    private LinkedHashSet<Pixel> pixels;

    // CONSTRUCTOR
    // EFFECTS: creates a new NotePanel with the given string
    //          throws NoTitleException if the given string length is 0
    public NotePanel(String title) throws NoTitleException {
        if (title.length() == 0) {
            throw new NoTitleException();
        }
        setLayout(new BorderLayout());
        setBackground(Color.white);
        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setVisible(true);
        textPane.setOpaque(false);
        add(textPane);

        pixels = new LinkedHashSet<>();
        this.title = title;
    }

    // EFFECTS: returns true if title is the same as the given string, false otherwise
    public boolean hasTitle(String s) {
        return s.equals(title);
    }

    // MODIFIES: this
    // EFFECTS: sets the text in textPane to the given string
    public void setBody(String body) {
        textPane.setText(body);
    }

    // EFFECTS: returns the NotePanel, converted to its corresponding NotePanelData object
    public NotePanelData toData() {
        return new NotePanelData(title, textPane.getText(), pixels);
    }

    // MODIFIES: this
    // EFFECTS: adds the given pixel to pixels
    public void addPixel(Pixel p) {
        pixels.add(p);
    }

    // MODIFIES: this
    // EFFECTS: removes the given pixel from pixels
    public void removePixel(Pixel p) {
        pixels.remove(p);
    }

    // MODIFIES: this
    // EFFECTS: sets the title to the given string
    public void setTitle(String t) {
        title = t;
    }

    // EFFECTS: returns the current title of NotePanel
    public String getTitle() {
        return title;
    }

    // MODIFIES: this
    // EFFECTS: disables / enables editing of textPane
    public void toggleEditable(boolean b) {
        textPane.setEditable(b);
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        pixels.stream().forEach((p) -> p.draw(g));
    }

    @Override
    public void addMouseMotionListener(MouseMotionListener l) {
        textPane.addMouseMotionListener(l);
    }

    /*
    public void setColour(Color colour) {
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setForeground(attributeSet, colour);
        textPane.setCharacterAttributes(attributeSet, true);
    }
     */
}
