package ui.options.creation;

import model.NotePanel;
import model.exceptions.NoTitleException;
import ui.NoteGUI;

import javax.swing.*;

public class NoteCreationGUI extends CreationGUI {

    // CONSTRUCTOR
    // EFFECTS: creates a new NoteCreationGUI
    public NoteCreationGUI() {
        super("Note Creation");
        addUIElements();
        validate();
    }

    // EFFECTS: makes and returns the create button
    //          when clicked, creates a new note with the given string in pane
    protected JButton makeButton() {
        super.makeButton("Create Note");
        button.addActionListener(e -> {
            try {
                new NoteGUI(new NotePanel(pane.getText()));
                dispose();
            } catch (NoTitleException noTitleException) {
                createTitleWarning();
            }
        });
        return button;
    }
}
