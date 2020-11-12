package model;

import model.exceptions.NoTitleException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Writable {
    private String category;
    private List<NotePanel> allNotes;

    // CONSTRUCTOR
    // EFFECTS: sets the category name
    public Category(String name) throws NoTitleException {
        if (name.length() == 0) {
            throw new NoTitleException();
        }
        category = name;
        allNotes = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the note into the given category
    public void addNotes(NotePanel n) {
        allNotes.add(n);
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
        for (NotePanel notes: allNotes) {
            NotePanelData data = notes.toData();
            jsonArray.put(data.toJson());
        }
        return jsonArray;
    }
}
