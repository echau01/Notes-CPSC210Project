package model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String category;
    private List<Notes> allNotes;

    // CONSTRUCTOR
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

    // REQUIRES: given note must be in category
    // MODIFIES: this
    // EFFECTS: removes the given note
    public void removeNotes(Notes n) {
        allNotes.remove(n);
    }

    // MODIFIES: this
    // EFFECTS: deletes the note with the given title, does nothing if there is no note with the title
    public void removeNotesByName(String s) {
        for (Notes n: allNotes) {
            if (n.hasTitle(s)) {
                removeNotes(n);
                break;
            }
        }
    }

    // REQUIRES: category contains a note at index i
    // EFFECTS: returns the title of the given note at index i
    public String getNoteByIndex(int i) {
        return allNotes.get(i).getTitle();
    }

    // REQUIRES: name is in lower case
    // EFFECTS: returns the category with the given name, returns a placeholder if there is no such category
    public Notes getNoteByName(String s) {
        for (Notes n: allNotes) {
            String noteName = n.getTitle().toLowerCase();
            if (s.equals(noteName)) {
                return n;
            }
        }
        return new Notes("");
    }

    // EFFECTS: returns true if the category contains the note with the given title
    public boolean containsNote(String title) {
        for (Notes n: allNotes) {
            if (n.hasTitle(title)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns the category length
    public int getLength() {
        return allNotes.size();
    }

    // EFFECTS: returns the category name
    public String getName() {
        return category;
    }

    // EFFECTS: renames the category to the given name
    public void setName(String name) {
        category = name;
    }


}
