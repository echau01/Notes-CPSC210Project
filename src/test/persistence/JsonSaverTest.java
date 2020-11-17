package persistence;

import model.*;
import model.exceptions.NoTitleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import static org.junit.jupiter.api.Assertions.*;

public class JsonSaverTest {

    CategoryContainer ctyc;
    Category c;
    NotePanel n;

    @BeforeEach
    void init() {
        try {
            ctyc = new CategoryContainer();
            c = new Category("category");
            n = new NotePanel("note");
        } catch (NoTitleException e) {
            fail("Caught NoTitleException");
        }
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
        } catch (NoTitleException e) {
            fail("caught NoTitleException");
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
        } catch (NoTitleException e) {
            fail("caught NoTitleException");
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
            Pixel pixel = new Pixel(1, 1, Color.black);
            n.setBody("entry");
            n.addPixel(pixel);
            c.addNotes(n);
            ctyc.addCategory(c);
            saver.save(ctyc);

            JsonParser parser = new JsonParser("./data/testJsonSaverAllFull.json");
            ctyc = parser.parseFile();
            assertTrue(ctyc.containsCategory("category"));
            assertTrue(ctyc.getCategoryByName("category").containsNote("note"));
            assertTrue(ctyc.getCategoryByName("category").getNoteByName("note").hasBody("entry"));
            assertTrue(ctyc.getCategoryByName("category").getNoteByName("note").getPixels().contains(pixel));
        } catch (NoTitleException e) {
            fail("caught NoTitleException");
        } catch (InvalidPathException e) {
            fail("caught InvalidPathException");
        } catch (IOException e) {
            fail("caught IOException");
        }
    }
}

