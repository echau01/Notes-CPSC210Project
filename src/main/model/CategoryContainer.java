package model;

import model.exceptions.NoCategoryException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

public class CategoryContainer implements Writable {
    private HashMap<String, Category> categories;

    // CONSTRUCTOR
    // EFFECTS: constructs an empty category container
    public CategoryContainer() {
        categories = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: constructs an empty category
    public void addCategory(Category c) {
        categories.put(c.getName(), c);
    }

    // MODIFIES: this
    // EFFECTS: removes the given category
    public void removeCategory(Category c) {
        categories.remove(c.getName());
    }

    public HashMap<String, Category> getCategories() {
        return categories;
    }

    public Set<Category> getCategoriesOnly() {
        Set<Category> categoriesKeyless = new HashSet<>();
        for (String key: categories.keySet()) {
            categoriesKeyless.add(categories.get(key));
        }
        return categoriesKeyless;
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
        for (Category cty: getCategoriesOnly()) {
            jsonArray.put(cty.toJson());
        }
        return jsonArray;
    }
}
