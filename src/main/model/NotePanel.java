package model;

import com.sun.xml.internal.ws.api.Component;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class NotePanel extends JPanel {

    private JTextPane textPane;
    private String title;
    private LinkedHashSet<Pixel> pixels;

    public NotePanel(String title) {
        setLayout(new BorderLayout());
        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setVisible(true);
        textPane.setOpaque(false);
        add(textPane);

        pixels = new LinkedHashSet<>();
        this.title = title;
    }

    public NotePanelData toData() {
        return new NotePanelData(title, textPane.getText(), pixels);
    }

    public void addPixel(Pixel p) {
        pixels.add(p);
    }

    public void removePixel(Pixel p) {
        pixels.remove(p);
    }

    public void setTitle(String t) {
        title = t;
    }

    public String getTitle() {
        return title;
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

    public void toggleEditable(boolean b) {
        textPane.setEditable(b);
    }

    public void setColour(Color colour) {
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setForeground(attributeSet, colour);
        textPane.setCharacterAttributes(attributeSet, true);
    }
}
