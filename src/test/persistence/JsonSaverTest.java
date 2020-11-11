package persistence;
/*
import model.Category;
import model.CategoryContainer;
import model.NotePanelData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.InvalidPathException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonSaverTest {

    CategoryContainer ctyc;
    Category c;
    NotePanelData n;

    @BeforeEach
    void init() {
        ctyc = new CategoryContainer();
        c = new Category("category");
        n = new NotePanelData("note");
    }

    @Test
    void testJsonSaverInvalidFile() {
        try {
            JsonSaver saver = new JsonSaver("\0");
            saver.save(ctyc);
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testJsonSaverAllEmpty() {
        try {
            JsonSaver saver = new JsonSaver("./data/testJsonSaverAllEmpty.json");
            saver.save(ctyc);
        } catch (IOException e) {
            fail("caught IOException");
        }
    }

    @Test
    void testJsonSaverOnlyCategoriesSaved() {
        try {
            JsonSaver saver = new JsonSaver("./data/testJsonSaverOnlyCategoriesSaved.json");
            ctyc.addCategory(c);
            saver.save(ctyc);

            JsonParser parser = new JsonParser("./data/testJsonSaverOnlyCategoriesSaved.json");
            ctyc = parser.parseFile();
            assertTrue(ctyc.containsCategory("category"));
        } catch (InvalidPathException e) {
            fail("caught InvalidPathException");
        } catch (IOException e) {
            fail("caught IOException");
        }
    }

    @Test
    void testJsonSaverCategoryAndEmptyNotesSaved() {
        try {
            JsonSaver saver = new JsonSaver("./data/testJsonSaverCategoryAndEmptyNotesSaved.json");
            c.addNotes(n);
            ctyc.addCategory(c);
            saver.save(ctyc);

            JsonParser parser = new JsonParser("./data/testJsonSaverCategoryAndEmptyNotesSaved.json");
            ctyc = parser.parseFile();
            assertTrue(ctyc.containsCategory("category"));
            assertTrue(ctyc.getCategoryByName("category").containsNote("note"));
        } catch (InvalidPathException e) {
            fail("caught InvalidPathException");
        } catch (IOException e) {
            fail("caught IOException");
        }
    }

    @Test
    void testJsonSaverAllFull() {
        try {
            JsonSaver saver = new JsonSaver("./data/testJsonSaverAllFull.json");
            n.addEntryToBody("entry");
            c.addNotes(n);
            ctyc.addCategory(c);
            saver.save(ctyc);

            JsonParser parser = new JsonParser("./data/testJsonSaverAllFull.json");
            ctyc = parser.parseFile();
            assertTrue(ctyc.containsCategory("category"));
            assertTrue(ctyc.getCategoryByName("category").containsNote("note"));
            assertTrue(ctyc.getCategoryByName("category").getNoteByName("note").containsEntry("entry"));
        } catch (InvalidPathException e) {
            fail("caught InvalidPathException");
        } catch (IOException e) {
            fail("caught IOException");
        }
    }
}

 */
