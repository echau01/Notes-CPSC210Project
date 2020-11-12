package model;

import model.exceptions.NoCategoryException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;
import java.util.List;

public class CategoryContainer implements Writable {
    private List<Category> categories;

    // CONSTRUCTOR
    // EFFECTS: constructs an empty category container
    public CategoryContainer() {
        categories = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: constructs an empty category
    public void addCategory(Category c) {
        categories.add(c);
    }

    // MODIFIES: this
    // EFFECTS: removes the given category
    public void removeCategory(Category c) {
        categories.remove(c);
    }

    // REQUIRES: name is in lower case
    // EFFECTS: returns the category with the given name, returns a placeholder if there is no such category
    public Category getCategoryByName(String name) throws NoCategoryException {
        String ctyName;
        for (Category c:categories) {
            ctyName = c.getName().toLowerCase();
            if (name.equals(ctyName)) {
                return c;
            }
        }
        throw new NoCategoryException();
    }

    public List<Category> getCategories() {
        return categories;
    }

    // the method here is inspired by the JsonSerializationDemo app provided in the Phase 2 edX page
    // EFFECTS: converts this into a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("categories", categoriesToJsonArray());
        return jsonObject;
    }

    // EFFECTS: places every single category in categories into a single json array
    private JSONArray categoriesToJsonArray() {
        JSONArray jsonArray = new JSONArray();
        for (Category cty: categories) {
            jsonArray.put(cty.toJson());
        }
        return jsonArray;
    }
}
