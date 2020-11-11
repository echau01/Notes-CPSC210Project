package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.LinkedList;
import java.util.List;

// TODO: add documentation
public class NotePanelData implements Writable {
    private String title;
    private String body;
    private LinkedList<Pixel> pixels;

    // CONSTRUCTOR
    // EFFECTS: Creates a new note with the given title
    public NotePanelData(String title) {
        this.title = title;
        pixels = new LinkedList<>();
    }

    public void addPixel(Pixel p) {
        pixels.add(p);
    }

    // EFFECTS: gets title of note
    public String getTitle() {
        return title;
    }

    // EFFECTS: sets the title to the given string
    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public JSONObject toJson() {
        return null;
    }

}
