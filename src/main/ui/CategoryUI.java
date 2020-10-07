package ui;

import model.Category;
import model.Notes;

public class CategoryUI extends UI {

    Category cty;

    public CategoryUI(Category c) {
        cty = c;
        init();
    }

    @Override
    public void consoleUI() {
        if (cty.getLength() == 0) {
            System.out.println(cty.getName() + " currently has no notes. Create a new note? \n COMMANDS:"
                    + "\n\tC = Create new notes \n\tX = Exit category");
        } else {
            System.out.println("All notes under category: " + cty.getName());
            cty.printNoteNames();
            System.out.println("Enter the name of the notes you wish to access. Otherwise, please refer to commands."
                    + "\n COMMANDS: \n\tC = Create new notes \n\tD = Delete notes \n\tX = Exit category");
        }
    }

    @Override
    public void processCommands() {
        super.processCommands();

        if (cmd.equals("C")) {
            makeNotes();
        } else if (cmd.equals("D")) {
            deleteNotes();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new note
    // TODO: add a check to see if the category already exists - if it does, don't make the category
    private void makeNotes() {
        System.out.println("Enter the title of the notes.");

        cmd = keyInput.next();
        Notes n = new Notes(cmd);
        cty.addNotes(n);
    }

    // REQUIRES: length of allCategories is at least 1
    // MODIFIES: this
    // EFFECTS: deletes a category with the given name, print 'unknown name' otherwise
    private void deleteNotes() {
        System.out.println("Type in the title of the note you wish to delete. Your notes are:");
        cty.printNoteNames();

        cmd = keyInput.next();
        cty.removeNotes(cmd);
    }
}
