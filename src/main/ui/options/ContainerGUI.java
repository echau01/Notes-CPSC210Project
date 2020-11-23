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

import javax.swing.*;
import java.awt.*;

public abstract class ContainerGUI extends PopupGUI {

    private static final String DESTINATION = "./data/CategoryContainer.json";
    protected static final int WIDTH = 640;
    protected static final int HEIGHT = 480;
    protected static final int DIVIDER_SIZE = 0;

    protected final ToolsGUI toolsGUI;
    protected final NoteGUI noteGUI;
    protected final NotePanel notePane;
    protected model.CategoryContainer ctyc;

    public ContainerGUI(String name, ToolsGUI toolsGUI) {
        super(name, WIDTH, HEIGHT);

        this.toolsGUI = toolsGUI;
        notePane = toolsGUI.getNoteGUI().getNotePane();
        noteGUI = toolsGUI.getNoteGUI();

        loadCategoryContainer();
    }

    // EFFECTS: returns the button panel
    protected JPanel makeButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(createFirstButton());
        buttonPanel.add(createDeleteButton());
        buttonPanel.add(createCreationButton());
        return buttonPanel;
    }


    // EFFECTS: makes and returns the first button
    abstract JButton createFirstButton();

    // EFFECTS: makes and returns the delete button
    abstract JButton createDeleteButton();

    // EFFECTS: makes and returns the creation button
    abstract JButton createCreationButton();


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
