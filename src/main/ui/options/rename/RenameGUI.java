package ui.options.rename;

import ui.options.OptionsGUI;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public abstract class RenameGUI extends OptionsGUI {
    protected static final int WIDTH = 320;
    protected static final int HEIGHT = 240;
    private static final int DIVIDER_SIZE = 0;

    protected JTextPane pane;

    // CONSTRUCTOR
    // EFFECTS: makes a new RenameGUI with the given parameters
    public RenameGUI(String name) {
        super(name, WIDTH, HEIGHT);
    }

    // EFFECTS: initialises and adds the all the ui elements (buttons, text panels etc.) to the main window
    protected void addUIElements() {
        pane = new JTextPane();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        pane.setParagraphAttributes(center, true);
        pane.setBorder(BorderFactory.createTitledBorder("New Title"));
        pane.setSize(new Dimension(WIDTH / 10, HEIGHT / 4));

        JSplitPane divider = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pane, makeButton());
        divider.setDividerLocation(HEIGHT / 2);
        divider.setDividerSize(DIVIDER_SIZE);
        divider.setEnabled(false);
        add(divider);
    }

    // EFFECTS: makes a button
    abstract JButton makeButton();
}
