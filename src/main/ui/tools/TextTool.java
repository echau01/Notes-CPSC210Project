package ui.tools;

import model.NotePanel;
import java.awt.*;

// TODO: add a colour feature to textTool
public class TextTool {

    private NotePanel textPane;
    // private Color colour;

    // CONSTRUCTOR
    // EFFECTS: creates a new TextTool
    public TextTool(NotePanel textPane, Color c) {
        this.textPane = textPane;
        // colour = c;
        // textPane.setColour(colour);
    }

    // MODIFIES: this
    // EFFECTS: toggles whether or not the textPane is editable
    public void setActive(Boolean b) {
        if (b) {
            textPane.toggleEditable(true);
        } else {
            textPane.toggleEditable(false);
        }
    }
}
