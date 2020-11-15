package persistence;

import model.CategoryContainer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// the formatting of methods in this class takes inspiration from JsonSerializationDemo
public class JsonSaver {
    private PrintWriter printWriter;
    private final String filePath;
    private static int TAB = 4;

    // CONSTRUCTOR
    // EFFECTS: constructs a new jsonSaver with the given filePath
    public JsonSaver(String filePath) {
        this.filePath = filePath;
    }

    // MODIFIES: this
    // EFFECTS: prints the JSON representation of the given CategoryContainer as a string to a file
    public void save(CategoryContainer ctyCon) throws FileNotFoundException {
        initWriter();
        String jsonCtyCon = ctyCon.toJson().toString(TAB);
        printWriter.write(jsonCtyCon);
        terminate();
    }

    // MODIFIES: this
    // EFFECTS: initialises the printWriter object with the given file at filePath
    //          throws FileNotFoundException if the file is not found at filePath
    private void initWriter() throws FileNotFoundException {
        printWriter = new PrintWriter(new File(filePath));
    }

    // MODIFIES: this
    // EFFECTS: terminates athe printWriter object
    private void terminate() {
        printWriter.close();
    }
}
