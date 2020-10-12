package model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String category;
    private List<Notes> allNotes;

    // EFFECTS: sets the category name
    public Category(String name) {
        category = name;
        allNotes = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the note into the given category
    public void addNotes(Notes n) {
        allNotes.add(n);
    }

    // MODIFIES: this
    // EFFECTS: deletes the note with the given title, does nothing if there is no note with the title
    public void removeNotes(String s) {
        for (Notes n: allNotes) {
            if (n.hasTitle(s)) {
                allNotes.remove(n);
                break;
            }
        }
    }

    // REQUIRES: name is in lower case
    // EFFECTS: returns the category with the given name, returns a placeholder if there is no such category
    public Notes returnNoteWithName(String s) {
        for (Notes n: allNotes) {
            String noteName = n.getTitle().toLowerCase();
            if (s.equals(noteName)) {
                return n;
            }
        }
        return new Notes("");
    }

    // EFFECTS: returns the category name
    public String getName() {
        return category;
    }

    // EFFECTS: renames the category to the given name
    public void rename(String name) {
        category = name;
    }

    // EFFECTS: returns the category length
    public int getLength() {
        return allNotes.size();
    }

    // EFFECTS: prints all note names
    public void printNoteNames() {
        for (Notes n:allNotes) {
            System.out.println("\t" + n.getTitle());
        }
    }
}
