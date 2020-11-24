package ui.tools;

import ui.ToolsGUI;

public class TextTool {

    private final ToolsGUI toolsGUI;

    // CONSTRUCTOR
    // EFFECTS: creates a new TextTool
    public TextTool(ToolsGUI toolsGUI) {
        this.toolsGUI = toolsGUI;
    }

    // MODIFIES: this
    // EFFECTS: toggles whether or not the textPane is editable
    public void setActive(Boolean b) {
        toolsGUI.getNoteGUI().getNotePane().toggleEditable(b);
    }
}
