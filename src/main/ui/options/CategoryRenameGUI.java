package ui.options;

import model.exceptions.NoTitleException;
import ui.PopupGUI;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class CategoryRenameGUI extends PopupGUI {
    protected static final int WIDTH = 320;
    protected static final int HEIGHT = 240;
    private static final int DIVIDER_SIZE = 0;

    private final CategoryContainerGUI ctycGUI;
    private JTextPane pane;

    // https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
    // CONSTRUCTOR
    // EFFECTS: creates a new CategoryRenameGUI
    public CategoryRenameGUI(CategoryContainerGUI ctycGUI) {
        super("Rename Note", WIDTH, HEIGHT);
        this.ctycGUI = ctycGUI;

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
        pane.setBorder(BorderFactory.createTitledBorder("Category Name"));
        pane.setSize(new Dimension(WIDTH / 10, HEIGHT / 4));

        JSplitPane divider = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pane, makeButton());
        divider.setDividerLocation(HEIGHT / 2);
        divider.setDividerSize(DIVIDER_SIZE);
        divider.setEnabled(false);
        add(divider);
    }

    // EFFECTS: makes and returns the rename button
    //          when clicked, renames the category to the string in pane
    private JButton makeButton() {
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
