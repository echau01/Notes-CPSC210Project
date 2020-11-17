package ui.options;

import model.Category;
import model.NotePanel;
import model.exceptions.NoTitleException;
import persistence.JsonParser;
import persistence.JsonSaver;
import ui.ErrorGUI;
import ui.NoteGUI;
import ui.PopupGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CategoryContainerGUI extends PopupGUI {
    protected static final int WIDTH = 640;
    protected static final int HEIGHT = 480;
    private static final int DIVIDER_SIZE = 0;
    private static final String DESTINATION = "./data/CategoryContainer.json";

    private final NoteGUI noteGUI;
    private final NotePanel notePane;
    private model.CategoryContainer ctyc;

    private JList ctycPanel;

    // CONSTRUCTOR
    // EFFECTS: creates a new CategoryContainerGUI
    public CategoryContainerGUI(NoteGUI noteGUI, NotePanel notePane) {
        super("All Categories", WIDTH, HEIGHT);
        this.notePane = notePane;
        this.noteGUI = noteGUI;

        loadCategoryContainer();
        addUIElements();
    }

    // https://stackoverflow.com/questions/4262669/refresh-jlist-in-a-jframe/4262716
    // MODIFIES: this
    // EFFECTS: adds a new category to category container, then saves ctyc and refreshes the gui
    public void addCategory(Category c) {
        ctyc.addCategory(c);
        saveCategoryContainer();
        refresh();
    }

    // MODIFIES: this
    // EFFECTS: sets the name of the selected category to the given string, then saves ctyc and refreshes the gui
    //          throws a NoTitleException if the given name is blank
    public void renameCategory(String name) throws NoTitleException {
        if (name.length() == 0) {
            throw new NoTitleException();
        }
        String selected = ctycPanel.getSelectedValue().toString();
        Category selectedCty = ctyc.getCategoryByName(selected);
        selectedCty.setName(name);
        saveCategoryContainer();
        refresh();
    }

    // https://stackoverflow.com/questions/4344682/double-click-event-on-jlist-element
    // MODIFIES: this
    // EFFECTS: initialises and adds the all the ui elements (buttons, text panels etc.) to the main window
    @Override
    protected void addUIElements() {
        ctycPanel = new JList(getAllCategoryNames());
        ctycPanel.setBorder(BorderFactory.createTitledBorder("Categories"));
        ctycPanelAddMouseListener();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(createRenameButton());
        buttonPanel.add(createDeleteButton());
        buttonPanel.add(createCategoryButton());

        JSplitPane divider = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctycPanel, buttonPanel);
        divider.setDividerLocation(HEIGHT - HEIGHT / 4);
        divider.setDividerSize(DIVIDER_SIZE);
        divider.setEnabled(false);
        add(divider);
    }

    // EFFECTS: creates the button which performs the rename category operation
    private JButton createRenameButton() {
        super.makeButton("Rename Selected");
        button.addActionListener(e -> createRenameCategoryGUI());
        return button;
    }

    // EFFECTS: creates the delete button which performs the delete category operation
    private JButton createDeleteButton() {
        super.makeButton("Delete Selected");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCategory();
            }

            // Nested Method
            // MODIFIES: this
            // EFFECTS: deletes the selected category - saves ctyc and refreshes ui afterwards to reflect the change
            private void deleteCategory() {
                try {
                    String selected = ctycPanel.getSelectedValue().toString();
                    ctyc.deleteCategory(selected);
                    saveCategoryContainer();
                    refresh();
                } catch (NullPointerException e) {
                    new ErrorGUI("Please select a Category.", "Error");
                }
            }
        });
        return button;
    }

    // EFFECTS: creates the create category button which performs the make category operation
    private JButton createCategoryButton() {
        super.makeButton("New Category");
        button.addActionListener(e -> makeCategoryCreationUI());
        return button;
    }

    // EFFECTS: creates a new window which provides the steps needed to make a new category
    private void makeCategoryCreationUI() {
        new CategoryCreationGUI(this);
    }

    // EFFECTS: creates a new window which provides the steps needed to rename the selected category
    private void createRenameCategoryGUI() {
        try {
            String selected = ctycPanel.getSelectedValue().toString();
            new CategoryRenameGUI(this);
            saveCategoryContainer();
        } catch (NullPointerException e) {
            new ErrorGUI("Please select a Category.", "Error");
        }
    }

    // MODIFIES: this
    // EFFECTS: saves the ctyc to file
    private void saveCategoryContainer() {
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
    private void loadCategoryContainer() {
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

    // EFFECTS: adds a mouse listener to ctycPanel - the mouse listener will check for double clicks on selected items
    //          creates a new categoryGUI with the selected category if item is double clicked
    private void ctycPanelAddMouseListener() {
        ctycPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = (JList) e.getSource();
                // Double-click detected
                if (e.getClickCount() == 2) {
                    String key = list.getSelectedValue().toString();
                    Category c = ctyc.getCategories().get(key);
                    new CategoryGUI(noteGUI, ctyc, c, notePane);
                    dispose();
                }
            }
        });
    }

    // EFFECTS: returns a DefaultListModel of all the category names in ctyc
    private DefaultListModel<String> getAllCategoryNames() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String name : ctyc.getCategories().keySet()) {
            model.addElement(name);
        }
        return model;
    }

    // MODIFIES: this
    private void refresh() {
        new CategoryContainerGUI(noteGUI, notePane);
        dispose();
    }
}
