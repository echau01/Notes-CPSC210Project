package model;

import model.exceptions.NoCategoryException;
import model.exceptions.NoTitleException;
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

    // MODIFIES: this
    // EFFECTS: if a category matches the given string, the category is deleted, otherwise do nothing
    public void removeCategoryByName(String s) {
        for (Category c:getCategoriesOnly()) {
            if (s.equals(c.getName())) {
                categories.remove(s);
                break;
            }
        }
    }

    // EFFECTS: returns the category with the given name, returns a placeholder if there is no such category
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
