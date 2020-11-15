package ui.options;

import model.NotePanel;
import model.exceptions.NoTitleException;
import ui.NoteGUI;
import ui.PopupGUI;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteCreationGUI extends PopupGUI {
    protected static final int WIDTH = 320;
    protected static final int HEIGHT = 240;
    private static final int DIVIDER_SIZE = 0;

    private JTextPane pane;

    // CONSTRUCTOR
    // EFFECTS: creates a new NoteCreationGUI
    public NoteCreationGUI() {
        super("Note Creation", WIDTH, HEIGHT);
        addUIElements();
        validate();
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
    //          when clicked, creates a new note with the given string in pane
    private JButton makeButton() {
        super.makeButton("Create Note");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new NoteGUI(new NotePanel(pane.getText()));
                    dispose();
                } catch (NoTitleException noTitleException) {
                    createTitleWarning();
                }
            }
        });
        return button;
    }
}
