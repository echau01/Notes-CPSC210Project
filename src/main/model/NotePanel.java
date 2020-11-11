package model;

import com.sun.xml.internal.ws.api.Component;
import org.json.JSONObject;
import persistence.Writable;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class NotePanel extends JPanel implements Writable {

    private JTextPane textPane;
    private LinkedHashSet<Pixel> pixels;
    private NotePanelData panelData;

    public NotePanel(String title) {
        panelData = new NotePanelData(title);

        setLayout(new BorderLayout());
        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setVisible(true);
        textPane.setOpaque(false);
        add(textPane);
        pixels = new LinkedHashSet<>();
    }

    public void addPixel(Pixel p) {
        pixels.add(p);
    }

    public void removePixel(Pixel p) {
        pixels.remove(p);
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

    @Override
    public JSONObject toJson() {
        return null;
    }
}
