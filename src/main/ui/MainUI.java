package ui;

import model.Category;
import model.CategoryContainer;


public class MainUI extends UI {
    private final CategoryContainer ALL_CATEGORIES;

    // CONSTRUCTOR
    // EFFECTS: runs the category ui
    public MainUI() {
        ALL_CATEGORIES = new CategoryContainer();
        init();
    }

    // EFFECTS: prints out the terminal ui
    @Override
    public void consoleUI() {
        if (ALL_CATEGORIES.getLength() == 0) {
            System.out.println("You currently have no categories. Please refer to commands."
                    + "\n COMMANDS: \n\tC = Create new category \n\tX = Terminate program");
        } else {
            System.out.println("Your note categories are: ");
            printCategoryNames();
            System.out.println("Enter the name of the category you wish to access. Otherwise, please refer to commands."
                    + "\n COMMANDS: \n\tD = Delete Category \n\tC = Make Category"
                    + "\n\tX = Terminate Program");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    @Override
    public void processCommands() {
        super.processCommands();
        Category categoryFromKeyInput = ALL_CATEGORIES.getCategoryByName(cmd);

        if (cmd.equals("c")) {
            makeCategory();
        } else if (cmd.equals("d")) {
            deleteCategory();
        } else if (categoryFromKeyInput.getName() != "") {
            new CategoryUI(categoryFromKeyInput);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new category
    // TODO: add a check to see if the category already exists - if it does, don't make the category
    private void makeCategory() {
        System.out.println("Type in the name of the category you wish to create.");

        cmd = keyInput.next();
        cmd += keyInput.nextLine();

        Category c = new Category(cmd);
        ALL_CATEGORIES.addCategory(c);
    }

    // REQUIRES: length of allCategories is at least 1
    // MODIFIES: this
    // EFFECTS: deletes a category with the given name, print 'unknown name' otherwise
    private void deleteCategory() {
        System.out.println("Type in the name of the category you wish to delete. Your categories are:");
        printCategoryNames();

        cmd = keyInput.next();
        cmd += keyInput.nextLine();

        ALL_CATEGORIES.deleteCategory(cmd);
    }

    // EFFECTS: prints all note names
    private void printCategoryNames() {
        for (int i = 0; i < ALL_CATEGORIES.getLength(); i++) {
            System.out.println("\t" + ALL_CATEGORIES.getCategoryNameByIndex(i));
        }
    }
}