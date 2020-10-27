package ui;

import model.Category;
import model.CategoryContainer;
import persistence.JsonSaver;

import java.io.FileNotFoundException;


public class MainUI extends UI {
    private final CategoryContainer allCategories;
    private static final String DESTINATION = "./data/CategoryContainer.json";
    private JsonSaver jsonSaver;

    // CONSTRUCTOR
    // EFFECTS: runs the category ui
    public MainUI() {
        allCategories = new CategoryContainer();
        jsonSaver = new JsonSaver(DESTINATION);
        init();
    }

    // EFFECTS: prints out the terminal ui
    @Override
    public void consoleUI() {
        if (allCategories.getLength() == 0) {
            System.out.println("You currently have no categories. Please refer to commands."
                    + "\n COMMANDS: \n\tC = Create new category \n\tX = Terminate program");
        } else {
            System.out.println("Your note categories are: ");
            printCategoryNames();
            System.out.println("Enter the name of the category you wish to access. Otherwise, please refer to commands."
                    + "\n COMMANDS: \n\tD = Delete Category \n\tC = Make Category"
                    + "\n\tS = Save Categories \n\tX = Terminate Program");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    @Override
    public void processCommands() {
        super.processCommands();
        Category categoryFromKeyInput = allCategories.getCategoryByName(cmd);

        if (cmd.equals("c")) {
            makeCategory();
        } else if (cmd.equals("d")) {
            deleteCategory();
        } else if (cmd.equals("s")) {
            saveCategories();
        } else if (categoryFromKeyInput.getName() != "") {
            new CategoryUI(categoryFromKeyInput);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new category
    private void makeCategory() {
        System.out.println("Type in the name of the category you wish to create.");

        cmd = keyInput.next();
        cmd += keyInput.nextLine();

        Category c = new Category(cmd);
        allCategories.addCategory(c);
    }

    // REQUIRES: length of allCategories is at least 1
    // MODIFIES: this
    // EFFECTS: deletes a category with the given name, print 'unknown name' otherwise
    private void deleteCategory() {
        System.out.println("Type in the name of the category you wish to delete. Your categories are:");
        printCategoryNames();

        cmd = keyInput.next();
        cmd += keyInput.nextLine();

        allCategories.removeCategoryByName(cmd);
    }

    private void saveCategories() {
        try {
            jsonSaver.initWriter();
            jsonSaver.save(allCategories);
            jsonSaver.terminate();
            System.out.println("Files successfully saved to " + DESTINATION);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot save to " + DESTINATION);
        }
    }

    // EFFECTS: prints all note names
    private void printCategoryNames() {
        for (int i = 0; i < allCategories.getLength(); i++) {
            System.out.println("\t" + allCategories.getCategoryNameByIndex(i));
        }
    }
}