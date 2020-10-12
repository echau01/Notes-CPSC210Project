package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {
    private Category cty;

    @BeforeEach
    void testInit() {
        cty = new Category("");
    }

    @Test
    void testAddNotesOnce() {
        cty.addNotes(new Notes(""));
        assertEquals(1, cty.getLength());
    }

    @Test
    void testAddNotesFiveHundred() {
        for (int i = 0; i < 500; i++) {
            String j = Integer.toString(i);
            cty.addNotes(new Notes(j));
            assertTrue(cty.containsNote(j));
        }

        assertEquals(500, cty.getLength());
    }

    @Test
    void testRemoveNonExistentNotes() {
        cty.addNotes(new Notes(""));
        assertEquals(1, cty.getLength());

        cty.removeNotesByName("a");
        assertEquals(1, cty.getLength());
    }

    @Test
    void testRemoveNotesOnce() {
        cty.addNotes(new Notes(""));
        assertEquals(1, cty.getLength());

        cty.removeNotesByName("");
        assertEquals(0, cty.getLength());
    }

    @Test
    void testRemoveNotesFiveHundred() {
        for (int i = 0; i < 500; i++) {
            String j = Integer.toString(i);
            cty.addNotes(new Notes(j));
        }

        assertEquals(500, cty.getLength());
        assertTrue(cty.containsNote("484"));
        assertTrue(cty.containsNote("251"));
        assertTrue(cty.containsNote("0"));

        cty.removeNotesByName("484");
        cty.removeNotesByName("251");
        cty.removeNotesByName("0");
        // these should result in doing nothing
        cty.removeNotesByName("693");
        cty.removeNotesByName("-576");

        assertEquals(497, cty.getLength());
        assertFalse(cty.containsNote("484"));
        assertFalse(cty.containsNote("251"));
        assertFalse(cty.containsNote("0"));
    }

    @Test
    void testReturnNoteByName() {
        for (int i = 0; i < 500; i++) {
            String j = Integer.toString(i);
            cty.addNotes(new Notes(j));
        }

        Notes n = cty.getNoteByName("484");
        Notes n2 = cty.getNoteByName("-576");

        assertEquals("484", n.getTitle());
        assertEquals("", n2.getTitle());
    }

    @Test
    void testReturnNoteByIndex() {
        for (int i = 0; i < 500; i++) {
            String j = Integer.toString(i);
            cty.addNotes(new Notes(j));
        }

        assertEquals("484", cty.getNoteByIndex(484));
        assertEquals("251", cty.getNoteByIndex(251));
    }

    @Test
    void testSetName() {
        assertEquals("", cty.getName());
        cty.setName("a");
        assertEquals("a", cty.getName());
    }

}
