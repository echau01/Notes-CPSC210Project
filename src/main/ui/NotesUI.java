package ui;

import model.Notes;

import static java.lang.Math.abs;

public class NotesUI extends UI {

    Notes note;

    // EFFECTS: constructs the notes ui
    public NotesUI(Notes note) {
        this.note = note;
        init();
    }

    // EFFECTS: prints out the terminal ui
    @Override
    public void consoleUI() {
        System.out.println("Viewing note: " + note.getTitle());
        printAllLines();
        System.out.println("\nTo add on to the notes, type anything and hit enter. "
                + "\n COMMANDS: \n\tR = Rename This Note \n\tD = Delete Line \n\tX = Exit Note");
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    @Override
    public void processCommands() {
        super.processCommands();

        if (cmd.equals("r")) {
            renameNote();
        } else if (cmd.equals("d")) {
            deleteLine();
        } else if (cmd.equals("x")) {
            ;
        } else {
            note.addEntryToBody(cmd2);
        }
    }

    // MODIFIES: this
    // EFFECTS: deletes a line with index given by key input
    private void deleteLine() {
        System.out.println("Delete which line?");
        cmd = keyInput.next();

        try {
            Integer indexNumber = abs(Integer.parseInt(cmd) - 1);
            if (note.withinIndex(indexNumber)) {
                note.deleteBodyByIndex(indexNumber);
            }
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid number.");
        }
    }

    // MODIFIES: this
    // EFFECTS: renames the note to the given key input
    private void renameNote() {
        System.out.println("Rename this note to:");
        cmd = keyInput.next();
        cmd += keyInput.nextLine();
        note.setTitle(cmd);
    }

    // EFFECTS: prints out all entries in body, with their corresponding line numbers
    private void printAllLines() {
        for (int i = 0; i < note.getLength(); i++) {
            System.out.println(note.getEntryWithLineNumber(i));
        }
    }


}
