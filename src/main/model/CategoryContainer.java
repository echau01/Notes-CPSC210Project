package model;

import java.util.ArrayList;
import java.util.List;

public class CategoryContainer implements Container {
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

    // REQUIRES: given category must be in category container
    // MODIFIES: this
    // EFFECTS: removes the given category
    public void removeCategory(Category c) {
        categories.remove(c);
    }

    // MODIFIES: this
    // EFFECTS: if a category matches the given string, the category is deleted, otherwise do nothing
    public void removeCategoryByName(String s) {
        for (Category c:categories) {
            if (s.equals(c.getName())) {
                removeCategory(c);
                break;
            }
        }
    }

    // EFFECTS: prints out the name of every category
    public String getCategoryNameByIndex(int i) {
        return categories.get(i).getName();
    }

    // REQUIRES: name is in lower case
    // EFFECTS: returns the category with the given name, returns a placeholder if there is no such category
    public Category getCategoryByName(String name) {
        String ctyName;
        for (Category c:categories) {
            ctyName = c.getName().toLowerCase();
            if (name.equals(ctyName)) {
                return c;
            }
        }
        return new Category("");
    }

    public boolean containsCategory(String name) {
        for (Category c: categories) {
            if (name.equals(c.getName())) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns number categories in category container
    public Integer getLength() {
        return categories.size();
    }
}
