package ui.options;

import model.Category;
import model.exceptions.NoTitleException;
import ui.PopupGUI;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class CategoryCreationGUI extends PopupGUI {
    protected static final int WIDTH = 320;
    protected static final int HEIGHT = 240;
    private static final int DIVIDER_SIZE = 0;

    private JTextPane pane;
    private final CategoryContainerGUI optionsGUI;

    // CONSTRUCTOR
    // EFFECTS: creates a new CategoryCreationGUI
    public CategoryCreationGUI(CategoryContainerGUI optionsGUI) {
        super("Category Creation", WIDTH, HEIGHT);
        this.optionsGUI = optionsGUI;

        addUIElements();
    }

    // MODIFIES: this
    // EFFECTS: initialises and adds the all the ui elements (buttons, text panels etc.) to the main window
    @Override
    protected void addUIElements() {
        pane = new JTextPane();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        pane.setParagraphAttributes(center, true);
        pane.setBorder(BorderFactory.createTitledBorder("Note Title"));
        pane.setSize(new Dimension(WIDTH / 10, HEIGHT / 4));

        JSplitPane divider = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pane, makeButton());
        divider.setDividerLocation(HEIGHT / 2);
        divider.setDividerSize(DIVIDER_SIZE);
        divider.setEnabled(false);
        add(divider);
    }

    // EFFECTS: makes and returns the create button
    //          when clicked, creates a new category
    private JButton makeButton() {
        super.makeButton("Create Category");
        button.addActionListener(e -> {
            try {
                optionsGUI.addCategory(new Category(pane.getText()));
                dispose();
            } catch (NoTitleException noTitleException) {
                createTitleWarning();
            }
        });
        return button;
    }
}
