package persistence;

import model.Category;
import model.CategoryContainer;
import model.Notes;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonParser {
    StringBuilder fileToString;
    String filePath;

    public JsonParser(String filePath) {
        this.filePath = filePath;
    }

    public CategoryContainer parseFile() throws IOException {
        JSONObject jsonObject = fileToJson(filePath);
        return loadCategoryContainer(jsonObject);
    }

    // EFFECTS: returns the source file as a json object
    //          first reads the entire file line-by-line as a stream
    //          then converts the stream into a mutable string, then converts the string to json object
    private JSONObject fileToJson(String filePath) throws IOException {
        fileToString = new StringBuilder();

        Stream<String> fileStream = Files.lines(Paths.get(filePath));
        fileStream.forEach(str -> fileToString.append(str));
        fileStream.close();

        JSONObject jsonObject = new JSONObject(fileToString.toString());
        return jsonObject;
    }

    private CategoryContainer loadCategoryContainer(JSONObject json) {
        CategoryContainer ctyc = new CategoryContainer();
        JSONArray allCategoriesJsonArray = json.getJSONArray("categories");

        for (Object categoryObj: allCategoriesJsonArray) {
            JSONObject jsonCategory = (JSONObject) categoryObj;
            loadCategory(ctyc, jsonCategory);
        }

        return ctyc;
    }

    private void loadCategory(CategoryContainer ctyc, JSONObject jsonCategory) {
        // loads the category name
        String categoryName = jsonCategory.getString("category");
        Category category = new Category(categoryName);
        ctyc.addCategory(category);

        JSONArray jsonAllNotes = jsonCategory.getJSONArray("all notes");
        loadNotes(category, jsonAllNotes);
    }

    private void loadNotes(Category cty, JSONArray jsonAllNotes) {
        for (Object noteObj : jsonAllNotes) {
            JSONObject jsonNote = (JSONObject) noteObj;
            loadNote(cty, jsonNote);
        }
    }

    private void loadNote(Category cty, JSONObject jsonNote) {
        String noteTitle = jsonNote.getString("notes");
        Notes notes = new Notes(noteTitle);
        cty.addNotes(notes);

        JSONArray jsonNoteBody = jsonNote.getJSONArray("note body");
        loadBody(notes, jsonNoteBody);
    }

    private void loadBody(Notes notes, JSONArray jsonNoteBody) {
        for (Object noteBody : jsonNoteBody) {
            String body = noteBody.toString();
            notes.addEntryToBody(body);
        }
    }

}
