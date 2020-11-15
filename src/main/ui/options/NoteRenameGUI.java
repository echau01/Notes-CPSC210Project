package ui.options;

import model.exceptions.NoTitleException;
import ui.NoteGUI;
import ui.PopupGUI;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class NoteRenameGUI extends PopupGUI {
    protected static final int WIDTH = 320;
    protected static final int HEIGHT = 240;
    private static final int DIVIDER_SIZE = 0;

    private final NoteGUI noteGUI;
    private JTextPane pane;

    // https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
    // CONSTRUCTOR
    // EFFECTS: creates a new NoteRenameGUI
    public NoteRenameGUI(NoteGUI noteGUI) {
        super("Rename Note", WIDTH, HEIGHT);
        this.noteGUI = noteGUI;

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

    // EFFECTS: makes and returns the rename button
    //          when clicked, renames the note to the string in pane
    private JButton makeButton() {
        makeButton("Set Name");
        button.addActionListener(e -> {
            try {
                noteGUI.renameNote(pane.getText());
                dispose();
            } catch (NoTitleException noTitleException) {
                createTitleWarning();
            }
        });
        return button;
    }
}
