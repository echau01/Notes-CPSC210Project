package ui.options.rename;

import model.exceptions.NoTitleException;
import ui.options.CategoryContainerGUI;

import javax.swing.*;

public class CategoryRenameGUI extends RenameGUI {
    private final CategoryContainerGUI ctycGUI;

    // CONSTRUCTOR
    // EFFECTS: creates a new CategoryRenameGUI
    public CategoryRenameGUI(CategoryContainerGUI ctycGUI) {
        super("Rename Note");
        this.ctycGUI = ctycGUI;

        addUIElements();
    }

    // EFFECTS: makes and returns the rename button
    //          when clicked, renames the category to the string in pane
    @Override
    protected JButton makeButton() {
        makeButton("Set Name");
        button.addActionListener(e -> {
            try {
                ctycGUI.renameCategory(pane.getText());
                dispose();
            } catch (NoTitleException noTitleException) {
                createTitleWarning();
            }
        });
        return button;
    }
}
