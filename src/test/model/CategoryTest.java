package model;

import model.exceptions.NoTitleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CategoryTest {
    private Category cty;
    private NotePanel note;

    @BeforeEach
    void testInit() {
        try {
            cty = new Category("name");
            note = new NotePanel("title");
            assertEquals("name", cty.getName());
        } catch (NoTitleException e) {
            fail("caught NoTitleException");
        }
    }

    @BeforeEach
    void testInitFail() {
        try {
            cty = new Category("");
            fail("expected NoTitleException");
        } catch (NoTitleException e) {
            // Pass
        }
    }

    @Test
    void testAddNotesOnce() {
        cty.addNotes(note);
        assertEquals(1, cty.getLength());
        assertEquals(1, cty.getNotes().size());
        assertEquals("title", cty.getNoteByName("title").getTitle());
    }

    @Test
    void testAddNotesFiveHundred() {
        try {
            for (int i = 0; i < 500; i++) {
                String j = Integer.toString(i);
                cty.addNotes(new NotePanel(j));
                assertTrue(cty.containsNote(j));
            }
        } catch (NoTitleException e) {
            fail("caught NoTitleException");
        }

        assertEquals(500, cty.getLength());
    }

    @Test
    void testRemoveNonExistentNotes() {
        cty.addNotes(note);
        assertEquals(1, cty.getLength());

        cty.removeNotesByName("a");
        assertEquals(1, cty.getLength());
    }

    @Test
    void testRemoveNotesOnce() {
        cty.addNotes(note);
        assertEquals(1, cty.getLength());

        cty.removeNotesByName("title");
        assertEquals(0, cty.getLength());
    }

    @Test
    void testRemoveNotesFiveHundred() {
        try {
            for (int i = 0; i < 500; i++) {
                String j = Integer.toString(i);
                cty.addNotes(new NotePanel(j));
            }
        } catch (NoTitleException e) {
            fail("caught NoTitleException");
        }

        assertEquals(500, cty.getLength());
        assertEquals(500, cty.getNotesOnly().size());
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
    void testSetName() {
        try {
            assertEquals("name", cty.getName());
            cty.setName("a");
            assertEquals("a", cty.getName());
        } catch (NoTitleException e) {
            fail("caught NoTitleException");
        }
    }

    @Test
    void testSetNameFail() {
        try {
            assertEquals("name", cty.getName());
            cty.setName("");
            fail("expected NoTitleException");
        } catch (NoTitleException e) {
            // Pass
        }
    }

}