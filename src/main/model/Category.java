package model;

import model.exceptions.NoTitleException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.*;

public class Category implements Writable {
    private String category;
    private HashMap<String, NotePanel> allNotes;

    // CONSTRUCTOR
    // EFFECTS: creates a new Category with the given string
    //          throws NoTitleException if the given string length is 0
    public Category(String name) throws NoTitleException {
        if (name.length() == 0) {
            throw new NoTitleException();
        }
        category = name;
        allNotes = new HashMap<String, NotePanel>();
    }

    // MODIFIES: this
    // EFFECTS: deletes the note with the given title, does nothing if there is no note with the title
    public void removeNotesByName(String s) {
        for (NotePanel n: getNotesOnly()) {
            if (n.hasTitle(s)) {
                allNotes.remove(s);
                break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the note into the given category
    public void addNotes(NotePanel n) {
        allNotes.put(n.getTitle(), n);
    }

    // EFFECTS: returns the category name
    public String getName() {
        return category;
    }

    // EFFECTS: renames the category to the given name
    // throws NoTitleException if name length is zero
    public void setName(String name) throws NoTitleException {
        if (name.length() == 0) {
            throw new NoTitleException();
        }
        category = name;
    }

    // EFFECTS: returns a set of only the notes from the allNotes hashmap, without the keys
    public Set<NotePanel> getNotesOnly() {
        Set<NotePanel> allNotesKeyless = new HashSet<>();
        for (String key: allNotes.keySet()) {
            allNotesKeyless.add(allNotes.get(key));
        }
        return allNotesKeyless;
    }

    // EFFECTS: returns the hashmap for AllNotes
    public HashMap<String, NotePanel> getNotes() {
        return allNotes;
    }

    // the method here is inspired by the JsonSerializationDemo app provided in the Phase 2 edX page
    // EFFECTS: converts this into a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("category", category);
        jsonObject.put("all notes", allNotesToJsonArray());
        return jsonObject;
    }

    // EFFECTS: places every single note in category into a single json array
    private JSONArray allNotesToJsonArray() {
        JSONArray jsonArray = new JSONArray();
        for (NotePanel notes: getNotesOnly()) {
            NotePanelData data = notes.toData();
            jsonArray.put(data.toJson());
        }
        return jsonArray;
    }
}
