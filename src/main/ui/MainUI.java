package ui;

import model.Category;

import java.util.ArrayList;
import java.util.List;


public class MainUI extends UI {
    private List<Category> allCategories;

    // EFFECTS: runs the category ui
    public MainUI() {
        allCategories = new ArrayList<>();
        init();
    }


    // EFFECTS: prints out the terminal ui
    @Override
    public void consoleUI() {
        if (allCategories.size() == 0) {
            System.out.println("You currently have no categories. Please refer to commands."
                    + "\n COMMANDS: \n\tC = Create new category \n\tX = Terminate program");
        } else {
            System.out.println("Your note categories are: ");
            printCategoryNames();
            System.out.println("Enter the name of the category you wish to access. Otherwise, please refer to commands."
                    + "\n COMMANDS: \n\tD = Delete a category \n\tC = Make new category \n\tX = Terminate program");
        }
    }

    // I took some inspiration from the Teller app here when designing key inputs.
    // MODIFIES: this
    // EFFECTS: processes user input
    @Override
    public void processCommands() {
        super.processCommands();

        Category categoryNamedCmd = getCategoryByName(cmd);

        if (cmd.equals("C")) {
            makeCategory();
        } else if (cmd.equals("D")) {
            deleteCategory();
        } else if (cmd.equals(categoryNamedCmd.getName().toUpperCase())) {
            new CategoryUI(categoryNamedCmd);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new category
    // TODO: add a check to see if the category already exists - if it does, don't make the category
    private void makeCategory() {
        System.out.println("Type in the name of the category you wish to create.");

        cmd = keyInput.next();
        Category c = new Category(cmd);
        allCategories.add(c);
    }

    // REQUIRES: length of allCategories is at least 1
    // MODIFIES: this
    // EFFECTS: deletes a category with the given name, print 'unknown name' otherwise
    private void deleteCategory() {
        System.out.println("Type in the name of the category you wish to delete. Your categories are:");
        printCategoryNames();

        cmd = keyInput.next();
        for (Category c:allCategories) {
            if (cmd.equals(c.getName())) {
                allCategories.remove(c);
                break;
            }
        }
    }

    // EFFECTS: prints out the name of every category
    private void printCategoryNames() {
        for (Category c:allCategories) {
            System.out.println("\t" + c.getName());
        }
    }

    // REQUIRES: name is in all caps
    // EFFECTS: returns the category with the given name, returns a placeholder if there is no such category
    private Category getCategoryByName(String name) {
        String ctyName;
        for (Category c:allCategories) {
            ctyName = c.getName().toUpperCase();
            if (name.equals(ctyName)) {
                return c;
            }
        }
        return new Category("");
    }
}