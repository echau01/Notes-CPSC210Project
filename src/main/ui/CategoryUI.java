package ui;

import model.Category;
import model.Notes;

public class CategoryUI extends UI {
    Category cty;

    // CONSTRUCTOR
    // MODIFIES: this
    // EFFECTS: constructs the category ui
    public CategoryUI(Category c) {
        cty = c;
        init();
    }

    // EFFECTS: prints out the category ui
    @Override
    public void consoleUI() {
        if (cty.getLength() == 0) {
            System.out.println(cty.getName() + " currently has no notes. Create a new note? \n COMMANDS:"
                    + "\n\tR = Rename this category \n\tC = Create new notes \n\tX = Exit category");
        } else {
            System.out.println("All notes under category: " + cty.getName());
            printNoteNames();
            System.out.println("Enter the name of the notes you wish to access. Otherwise, please refer to commands."
                    + "\n COMMANDS: \n\tR = Rename This Category \n\tC = Create Notes \n\tD = Delete Notes"
                    + "\n\tX = Exit Category");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    @Override
    public void processCommands() {
        super.processCommands();

        Notes noteFromKeyInput = cty.getNoteByName(cmd);
        if (cmd.equals("c")) {
            makeNotes();
        } else if (cmd.equals("d")) {
            deleteNotes();
        } else if (cmd.equals("r")) {
            renameCategory();
        } else if (noteFromKeyInput.getTitle() != "") {
            new NotesUI(noteFromKeyInput);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new note
    // TODO: add a check to see if the category already exists - if it does, don't make the category
    private void makeNotes() {
        System.out.println("Enter the title of the notes.");

        cmd = keyInput.next();
        cmd += keyInput.nextLine();

        Notes n = new Notes(cmd);
        cty.addNotes(n);
    }

    // EFFECTS: prints all note names
    private void printNoteNames() {
        for (int i = 0; i < cty.getLength(); i++) {
            System.out.println("\t" + cty.getNoteByIndex(i));
        }
    }

    // REQUIRES: length of allCategories is at least 1
    // MODIFIES: this
    // EFFECTS: deletes a category with the given name, print 'unknown name' otherwise
    private void deleteNotes() {
        System.out.println("Type in the title of the note you wish to delete. Your notes are:");
        printNoteNames();

        cmd = keyInput.next();
        cmd += keyInput.nextLine();

        cty.removeNotesByName(cmd);
    }

    // MODIFIES: this
    // EFFECTS: renames category to the string given by key input
    private void renameCategory() {
        System.out.println("Rename this category to:");

        cmd = keyInput.next();
        cmd += keyInput.nextLine();

        cty.setName(cmd);
    }
}
