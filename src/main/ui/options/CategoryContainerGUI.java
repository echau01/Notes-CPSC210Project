package ui.options;

import model.Category;
import model.exceptions.NoTitleException;
import ui.ToolsGUI;
import ui.options.creation.CategoryCreationGUI;
import ui.options.rename.CategoryRenameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CategoryContainerGUI extends ContainerGUI {

    // CONSTRUCTOR
    // EFFECTS: creates a new CategoryContainerGUI
    public CategoryContainerGUI(ToolsGUI toolsGUI) {
        super("All Categories", toolsGUI);
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
        String selected = objPanel.getSelectedValue().toString();
        Category selectedCty = ctyc.getCategoryByName(selected);
        selectedCty.setName(name);
        saveCategoryContainer();
        refresh();
    }

    // EFFECTS: creates a new window which provides the steps needed to make a new category
    private void makeCategoryCreationUI() {
        new CategoryCreationGUI(this);
    }

    // EFFECTS: creates a new window which provides the steps needed to rename the selected category
    private void createRenameCategoryGUI() {
        try {
            String selected = objPanel.getSelectedValue().toString();
            new CategoryRenameGUI(this);
            saveCategoryContainer();
        } catch (NullPointerException e) {
            new ErrorGUI("Please select a Category.", "Error");
        }
    }

    // EFFECTS: selects the category with the given key
    private void selectCategory(String key) {
        cty = ctyc.getCategories().get(key);
        new CategoryGUI(this);
        dispose();
    }

    // EFFECTS: adds a mouse listener to ctycPanel - the mouse listener will check for double clicks on selected items
    //          creates a new categoryGUI with the selected category if item is double clicked
    @Override
    protected void objPanelAddMouseListener() {
        objPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = (JList) e.getSource();
                // Double-click detected
                if (e.getClickCount() == 2) {
                    String key = list.getSelectedValue().toString();
                    selectCategory(key);
                }
            }
        });
    }

    // EFFECTS: returns a DefaultListModel of all the category names in ctyc
    @Override
    protected DefaultListModel<String> getAllObjectNames() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String name: ctyc.getCategories().keySet()) {
            model.addElement(name);
        }
        return model;
    }

    // EFFECTS: creates the button which performs the rename category operation
    @Override
    protected JButton createFirstButton() {
        super.makeButton("Rename Selected");
        button.addActionListener(e -> createRenameCategoryGUI());
        return button;
    }

    // EFFECTS: creates the delete button which performs the delete category operation
    @Override
    protected JButton createDeleteButton() {
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
                    String selected = objPanel.getSelectedValue().toString();
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
    @Override
    protected JButton createCreationButton() {
        super.makeButton("New Category");
        button.addActionListener(e -> makeCategoryCreationUI());
        return button;
    }

    // MODIFIES: this
    @Override
    protected void refresh() {
        new CategoryContainerGUI(toolsGUI);
        dispose();
    }
}
