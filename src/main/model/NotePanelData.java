package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.LinkedHashSet;

// TODO: add documentation
public class NotePanelData implements Writable {
    private String title;
    private String body;
    private LinkedHashSet<Pixel> pixels;

    // CONSTRUCTOR
    // EFFECTS: Creates a new note with the given title
    public NotePanelData(String title, String body, LinkedHashSet<Pixel> pixels) {
        this.title = title;
        this.body = body;
        this.pixels = pixels;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("body", body);
        jsonObject.put("pixels", pixelsToJsonArray());
        return jsonObject;
    }

    // EFFECTS: places every single line in note body into a single json array
    private JSONArray pixelsToJsonArray() {
        JSONArray jsonArray = new JSONArray();
        for (Pixel p: pixels) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }

}
