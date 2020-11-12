package ui.options;

import model.Category;
import ui.PopupGUI;

public class CategoryGUI extends PopupGUI {
    private Category category;

    CategoryGUI(Category c) {
        super(c.getName());
        category = c;
    }

    @Override
    protected void addUIElements() {

    }
}
