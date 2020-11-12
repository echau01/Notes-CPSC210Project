package persistence;

import model.*;
import model.exceptions.NoTitleException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// the formatting of methods in this class takes inspiration from JsonSerializationDemo
public class JsonParser {
    String filePath;

    // CONSTRUCTOR
    // EFFECTS: constructs a new jsonParser with the given filePath
    public JsonParser(String filePath) {
        this.filePath = filePath;
    }

    // EFFECTS: parses the json save file - converts the json text data and returns it
    //          throws IOException there are errors in reading the file
    public CategoryContainer parseFile() throws IOException, NoTitleException {
        JSONObject jsonObject = fileToJson(filePath);
        return loadCategoryContainer(jsonObject);
    }

    // https://stackoverflow.com/questions/326390/how-do-i-create-a-java-string-from-the-contents-of-a-file
    // EFFECTS: returns the source file as a json object - converts it to a string, then to a json object
    //          throws IOException there are errors in reading the file
    private JSONObject fileToJson(String filePath) throws IOException {
        String fileToString = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject jsonObject = new JSONObject(fileToString);
        return jsonObject;
    }

    // MODIFIES: ctyc
    // EFFECTS: parses all categories from json data
    private CategoryContainer loadCategoryContainer(JSONObject json) throws NoTitleException {
        CategoryContainer ctyc = new CategoryContainer();
        JSONArray allCategoriesJsonArray = json.getJSONArray("categories");

        for (Object categoryObj: allCategoriesJsonArray) {
            JSONObject jsonCategory = (JSONObject) categoryObj;
            loadCategory(ctyc, jsonCategory);
        }

        return ctyc;
    }

    // MODIFIES: ctyc
    // EFFECTS: parses individual category from json data and adds it to ctyc
    private void loadCategory(CategoryContainer ctyc, JSONObject jsonCategory) throws NoTitleException {
        String categoryName = jsonCategory.getString("category");
        Category category = new Category(categoryName);
        ctyc.addCategory(category);

        JSONArray jsonAllNotes = jsonCategory.getJSONArray("all notes");
        loadNotes(category, jsonAllNotes);
    }

    // MODIFIES: cty
    // EFFECTS: parses all notes from category json data
    private void loadNotes(Category cty, JSONArray jsonAllNotes) {
        for (Object noteObj : jsonAllNotes) {
            JSONObject jsonNote = (JSONObject) noteObj;
            loadNote(cty, jsonNote);
        }
    }

    // MODIFIES: cty
    // EFFECTS: parses individual notes from json data and adds it to cty
    private void loadNote(Category cty, JSONObject jsonNote) {
        String noteTitle = jsonNote.getString("title");
        NotePanel note = new NotePanel(noteTitle);

        String body = jsonNote.getString("body");
        note.addBody(body);

        JSONArray pixels = jsonNote.getJSONArray("pixels");
        loadPixels(note, pixels);

        cty.addNotes(note);
    }

    // MODIFIES: notes
    // EFFECTS: parses note body, adds every entry in note body to notes
    private void loadPixels(NotePanel notes, JSONArray pixels) {
        for (Object pixel : pixels) {
            JSONObject jsonPixel = (JSONObject) pixel;
            loadPixel(notes, jsonPixel);
        }
    }

    private void loadPixel(NotePanel notes, JSONObject jsonPixel) {
        int pixelX = jsonPixel.getInt("x");
        int pixelY = jsonPixel.getInt("y");
        int colourInt = jsonPixel.getInt("colour");
        Color colour = new Color(colourInt);
        notes.addPixel(new Pixel(pixelX, pixelY, colour));
    }
}

