package ui.options.rename;

import model.exceptions.NoTitleException;
import ui.NoteGUI;

import javax.swing.*;

public class NoteRenameGUI extends RenameGUI {
    private final NoteGUI noteGUI;

    // https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
    // CONSTRUCTOR
    // EFFECTS: creates a new NoteRenameGUI
    public NoteRenameGUI(NoteGUI noteGUI) {
        super("Rename Note");
        this.noteGUI = noteGUI;

        addUIElements();
    }

    // EFFECTS: makes and returns the rename button
    //          when clicked, renames the note to the string in pane
    @Override
    protected JButton makeButton() {
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
