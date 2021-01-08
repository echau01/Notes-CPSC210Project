package ui.options;

import model.Category;
import model.CategoryContainer;
import model.exceptions.NoTitleException;
import persistence.JsonParser;
import persistence.JsonSaver;
import ui.ToolsGUI;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class ContainerGUI extends OptionsGUI {

    private static final String DESTINATION = "./data/CategoryContainer.json";
    protected static final int WIDTH = 640;
    protected static final int HEIGHT = 480;
    protected static final int DIVIDER_SIZE = 0;

    protected final ToolsGUI toolsGUI;
    protected model.CategoryContainer ctyc;
    protected model.Category cty;

    protected JList objPanel;

    public ContainerGUI(String name, ToolsGUI toolsGUI) {
        super(name, WIDTH, HEIGHT);

        this.toolsGUI = toolsGUI;

        loadCategoryContainer();
    }

    // EFFECTS: returns the selected category
    public Category getCty() {
        return cty;
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

    // EFFECTS: initialises and adds the all the ui elements (buttons, text panels etc.) to the main window
    protected void addUIElements() {
        objPanel = new JList(getAllObjectNames());
        objPanel.setBorder(BorderFactory.createTitledBorder("Notes"));
        objPanelAddMouseListener();

        JPanel buttonPanel = makeButtonPanel();

        JSplitPane divider = new JSplitPane(JSplitPane.VERTICAL_SPLIT, objPanel, buttonPanel);
        divider.setDividerLocation(HEIGHT - HEIGHT / 4);
        divider.setDividerSize(DIVIDER_SIZE);
        divider.setEnabled(false);
        add(divider);
    }

    // EFFECTS: adds a mouse listener to objPanel
    protected abstract void objPanelAddMouseListener();

    // EFFECTS: returns the names of everything in the container in array form
    protected abstract DefaultListModel<String> getAllObjectNames();

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
        } catch (FileNotFoundException e) {
            new ErrorGUI("Error: file " + DESTINATION + " could not be accessed!", "Cannot save file.");
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
        } catch (IOException | NoTitleException e) {
            new ErrorGUI("Error loading saved files. Creating default files.", "File load error");
            generateDefaultFiles();
            refresh();
            // e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: generates default files to save
    private void generateDefaultFiles() {
        Category cty = new Category("Untitled");
        ctyc = new CategoryContainer();
        cty.addNotes(toolsGUI.getNoteGUI().getNotePane());
        ctyc.addCategory(cty);
        saveCategoryContainer();
    }

    abstract void refresh();
}
