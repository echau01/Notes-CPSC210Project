package ui.options.creation;

import model.Category;
import model.exceptions.NoTitleException;
import ui.options.CategoryContainerGUI;

import javax.swing.*;

public class CategoryCreationGUI extends CreationGUI {
    private final CategoryContainerGUI ctycGUI;

    // CONSTRUCTOR
    // EFFECTS: creates a new CategoryCreationGUI
    public CategoryCreationGUI(CategoryContainerGUI ctycGUI) {
        super("Category Creation");
        this.ctycGUI = ctycGUI;

        addUIElements();
    }

    // EFFECTS: makes and returns the create button
    //          when clicked, creates a new category
    @Override
    protected JButton makeButton() {
        super.makeButton("Create Category");
        button.addActionListener(e -> {
            try {
                ctycGUI.addCategory(new Category(pane.getText()));
                dispose();
            } catch (NoTitleException noTitleException) {
                createTitleWarning();
            }
        });
        return button;
    }
}
