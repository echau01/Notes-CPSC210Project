package persistence;

import model.CategoryContainer;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonSaver {
    private PrintWriter printWriter;
    private String filePath;
    private static int TAB = 4;

    public JsonSaver(String filePath) {
        this.filePath = filePath;
    }

    public void initWriter() throws FileNotFoundException {
        printWriter = new PrintWriter(new File(filePath));
    }

    // EFFECTS: prints the JSON representation of the given CategoryContainer as a string to a file
    public void save(CategoryContainer ctyCon) throws FileNotFoundException {
        initWriter();
        String jsonCtyCon = ctyCon.toJson().toString(TAB);
        printWriter.write(jsonCtyCon);
        terminate();
    }

    public void terminate() {
        printWriter.close();
    }

}
