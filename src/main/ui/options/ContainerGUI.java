package ui.options;

import model.Category;
import model.NotePanel;
import model.exceptions.NoTitleException;
import persistence.JsonParser;
import persistence.JsonSaver;
import ui.ErrorGUI;
import ui.NoteGUI;
import ui.PopupGUI;
import ui.ToolsGUI;
import ui.tools.Tools;

import javax.swing.*;
import java.awt.*;

public abstract class ContainerGUI extends PopupGUI {

    protected static final int WIDTH = 640;
    protected static final int HEIGHT = 480;
    private static final String DESTINATION = "./data/CategoryContainer.json";

    private final ToolsGUI toolsGUI;
    private final NoteGUI noteGUI;
    private final NotePanel notePane;
    private model.CategoryContainer ctyc;

    public ContainerGUI(String name, ToolsGUI toolsGUI) {
        super(name, WIDTH, HEIGHT);

        this.toolsGUI = toolsGUI;
        notePane = toolsGUI.getNoteGUI().getNotePane();
        noteGUI = toolsGUI.getNoteGUI();

        loadCategoryContainer();
    }

    // MODIFIES: this
    // EFFECTS: saves the ctyc to file
    protected void saveCategoryContainer() {
        JsonSaver jsonSaver = new JsonSaver(DESTINATION);
        try {
            jsonSaver.save(ctyc);
        } catch (Exception e) {
            new ErrorGUI("Error in saving files.", "Cannot save file.");
            // e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the ctyc from file
    //          if there is an error, default files are generated and loaded
    protected void loadCategoryContainer() {
        JsonParser jsonParser = new JsonParser(DESTINATION);
        try {
            ctyc = jsonParser.parseFile();
        } catch (Exception e) {
            new ErrorGUI("Error loading saved files. Creating default files.", "File load error");
            generateDefaultFiles();
            refresh();
            // e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: generates default files to save
    private void generateDefaultFiles() {
        try {
            Category cty = new Category("Untitled");
            ctyc = new model.CategoryContainer();
            cty.addNotes(notePane);
            ctyc.addCategory(cty);
            saveCategoryContainer();
        } catch (NoTitleException e) {
            new ErrorGUI("This should never be thrown. Something has gone horribly wrong.", "Error");
            e.printStackTrace();
        }
    }

    abstract void refresh();
}
