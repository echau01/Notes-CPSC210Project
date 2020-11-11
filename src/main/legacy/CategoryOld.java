package legacy;
/*
import model.NotePanelData;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class CategoryOld implements Writable {
    private String category;
    private List<NotePanelData> allNotes;

    // CONSTRUCTOR
    // EFFECTS: sets the category name
    public CategoryOld(String name) {
        category = name;
        allNotes = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the note into the given category
    public void addNotes(NotePanelData n) {
        allNotes.add(n);
    }

    // MODIFIES: this
    // EFFECTS: removes the given note
    public void removeNotes(NotePanelData n) {
        allNotes.remove(n);
    }

    // MODIFIES: this
    // EFFECTS: deletes the note with the given title, does nothing if there is no note with the title
    public void removeNotesByName(String s) {
        for (NotePanelData n: allNotes) {
            if (n.hasTitle(s)) {
                removeNotes(n);
                break;
            }
        }
    }

    // EFFECTS: returns the title of the given note at index i
    public String getNoteByIndex(int i) {
        return allNotes.get(i).getTitle();
    }

    // EFFECTS: returns the category with the given name, returns a placeholder if there is no such category
    public NotePanelData getNoteByName(String s) {
        for (NotePanelData n: allNotes) {
            String noteName = n.getTitle().toLowerCase();
            if (s.equals(noteName)) {
                return n;
            }
        }
        return new NotePanelData("");
    }

    // EFFECTS: returns true if the category contains the note with the given title
    public boolean containsNote(String title) {
        for (NotePanelData n: allNotes) {
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
        for (NotePanelData notes: allNotes) {
            jsonArray.put(notes.toJson());
        }
        return jsonArray;
    }
}

 */
