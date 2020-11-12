package ui.options;

import model.Category;
import model.CategoryContainer;
import model.NotePanel;
import persistence.JsonSaver;
import ui.PopupGUI;

import java.io.FileNotFoundException;

public class CategoryGUI extends PopupGUI {
    private static final String DESTINATION = "./data/CategoryContainer.json";

    private Category cty;
    private CategoryContainer ctyc;
    private NotePanel notePane;

    private JsonSaver jsonSaver;

    protected static final int WIDTH = 640;
    protected static final int HEIGHT = 480;

    CategoryGUI(CategoryContainer ctyc, Category cty, NotePanel notePane) {
        super(cty.getName(), WIDTH, HEIGHT);
        jsonSaver = new JsonSaver(DESTINATION);
        this.ctyc = ctyc;
        this.cty = cty;
        this.notePane = notePane;


    }

    @Override
    protected void addUIElements() {

    }
}
