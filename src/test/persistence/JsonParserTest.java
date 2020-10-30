package persistence;

import model.Category;
import model.CategoryContainer;
import model.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonParserTest {
    CategoryContainer ctyc;

    @Test
    void testJsonSaverInvalidFile() {
        try {
            JsonParser parser = new JsonParser("\0");
            ctyc = parser.parseFile();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testJsonSaverAllEmpty() {
        try {
            JsonParser parser = new JsonParser("./data/testJsonSaverAllEmpty.json");
            ctyc = parser.parseFile();
            assertEquals(0, ctyc.getLength());
        } catch(IOException e) {
            fail("caught IOException");
        }
    }

    @Test
    void testJsonSaverOnlyCategoriesSaved() {
        try {
            JsonParser parser = new JsonParser("./data/testJsonSaverOnlyCategoriesSaved.json");
            ctyc = parser.parseFile();
            assertTrue(ctyc.containsCategory("category"));
        } catch(IOException e) {
            fail("caught IOException");
        }
    }

    @Test
    void testJsonSaverCategoryAndEmptyNotesSaved() {
        try {
            JsonParser parser = new JsonParser("./data/testJsonSaverCategoryAndEmptyNotesSaved.json");
            ctyc = parser.parseFile();
            assertTrue(ctyc.containsCategory("category"));
            assertTrue(ctyc.getCategoryByName("category").containsNote("note"));
        } catch(IOException e) {
            fail("caught IOException");
        }
    }

    @Test
    void testJsonSaverAllFull() {
        try {
           JsonParser parser = new JsonParser("./data/testJsonSaverAllFull.json");
            ctyc = parser.parseFile();
            assertTrue(ctyc.containsCategory("category"));
            assertTrue(ctyc.getCategoryByName("category").containsNote("note"));
            assertTrue(ctyc.getCategoryByName("category").getNoteByName("note").containsEntry("entry"));
        } catch (IOException e) {
            fail("caught IOException");
        }
    }
}
