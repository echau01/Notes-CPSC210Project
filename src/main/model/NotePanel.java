package model;

import model.exceptions.NoTitleException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.util.LinkedHashSet;

public class NotePanel extends JPanel {

    private final JTextPane textPane;
    private String title;
    private final LinkedHashSet<Pixel> pixels;

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
    //          does nothing if the pixel is in the same x and y
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

    // EFFECTS: returns the body of the notes
    public String getBody() {
        return textPane.getText();
    }

    // EFFECTS: returns true if the given string matches the content in textPane
    public boolean hasBody(String body) {
        return textPane.getText().equals(body);
    }

    // EFFECTS: returns the pixels
    public LinkedHashSet<Pixel> getPixels() {
        return pixels;
    }

    // MODIFIES: this
    // EFFECTS: disables / enables editing of textPane
    public void toggleEditable(boolean b) {
        textPane.setEditable(b);
    }

    // EFFECTS: returns true if textPane is editable, false if not
    public boolean isTextEditable() {
        return textPane.isEditable();
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        pixels.forEach((p) -> p.draw(g));
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
