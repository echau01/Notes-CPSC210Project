package ui.tools;

import model.NotePanel;
import ui.ToolsGUI;

// TODO: add a colour feature to textTool
public class TextTool {

    private final NotePanel textPane;

    // CONSTRUCTOR
    // EFFECTS: creates a new TextTool
    public TextTool(ToolsGUI toolsGUI) {
        textPane = toolsGUI.getNoteGUI().getNotePane();
    }

    // MODIFIES: this
    // EFFECTS: toggles whether or not the textPane is editable
    public void setActive(Boolean b) {
        textPane.toggleEditable(b);
    }
}
