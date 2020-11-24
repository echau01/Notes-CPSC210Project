package ui.tools;

import model.NotePanel;
import model.Pixel;
import ui.ToolsGUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


// https://stackoverflow.com/questions/23122492/java-jframe-draw
public class PenTool implements MouseMotionListener {
    private final ToolsGUI toolsGUI;

    private Color colour;
    private boolean active;

    // CONSTRUCTOR
    // EFFECTS: creates a new PenTool
    public PenTool(ToolsGUI toolsGUI) {
        this.toolsGUI = toolsGUI;
        colour = toolsGUI.getSelectedColour();

        toolsGUI.getNoteGUI().getNotePane().addMouseMotionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: toggles whether PenTool is active or not
    public void setActive(Boolean b) {
        active = b;
    }

    // MODIFIES: this
    // EFFECTS: sets this colour to the given colour
    public void setColour(Color c) {
        colour = c;
    }

    // MODIFIES: this
    // EFFECTS: when mouse is dragged with button held down, and if the pen tool is active:
    //          paints pixel onto NotePanel
    @Override
    public void mouseDragged(MouseEvent e) {
        if (active) {
            NotePanel notePanel = toolsGUI.getNoteGUI().getNotePane();
            notePanel.addPixel(new Pixel(e.getX(), e.getY(), colour));
            notePanel.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
