package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.*;

public class CategoryContainer implements Writable {
    private final HashMap<String, Category> categories;

    // CONSTRUCTOR
    // EFFECTS: constructs an empty CategoryContainer
    public CategoryContainer() {
        categories = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: constructs an empty category
    public void addCategory(Category c) {
        categories.put(c.getName(), c);
    }

    // EFFECTS: returns true if categories contains category with given name
    public boolean containsCategory(String name) {
        return categories.containsKey(name);
    }

    // EFFECTS: returns the length of categories
    public int getLength() {
        return categories.size();
    }

    // MODIFIES: this
    // EFFECTS: removes the category with the given name
    public void deleteCategory(String name) {
        categories.remove(name);
    }

    // EFFECTS: returns categories
    public HashMap<String, Category> getCategories() {
        return categories;
    }

    // EFFECTS: returns the category with the given name
    public Category getCategoryByName(String name) {
        return categories.get(name);
    }

    // EFFECTS: returns a set of only the categories from the categories hashmap, without the keys
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
