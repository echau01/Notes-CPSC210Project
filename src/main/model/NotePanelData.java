package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.LinkedHashSet;

public class NotePanelData implements Writable {
    private final NotePanel notePanel;

    // CONSTRUCTOR
    // EFFECTS: Creates a new note with the given title
    public NotePanelData(NotePanel notePanel) {
        this.notePanel = notePanel;
    }

    // EFFECTS: returns the title assigned to the data
    public String getTitle() {
        return notePanel.getTitle();
    }

    // EFFECTS: returns the body of the notes
    public String getBody() {
        return notePanel.getBody();
    }

    // EFFECTS: returns the pixels
    public LinkedHashSet<Pixel> getPixels() {
        return notePanel.getPixels();
    }

    // EFFECTS: converts this into a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", notePanel.getTitle());
        jsonObject.put("body", notePanel.getBody());
        jsonObject.put("pixels", pixelsToJsonArray());
        return jsonObject;
    }

    // EFFECTS: places every single line in note body into a single json array
    private JSONArray pixelsToJsonArray() {
        JSONArray jsonArray = new JSONArray();
        for (Pixel p: notePanel.getPixels()) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }
}
