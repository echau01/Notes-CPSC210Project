package model;
/*
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesTest {

    private NotePanelData note;

    @BeforeEach
    void testInit() {
        note = new NotePanelData("");
    }

    @Test
    void testAddEntryToBodyOnce() {
        note.addEntryToBody("");

        assertTrue(note.containsEntry(""));
        assertEquals(1, note.getLength());
    }

    @Test
    void testAddEntryToBodyFiveHundred() {
        for (int i = 0; i < 500; i++) {
            String j = Integer.toString(i);
            note.addEntryToBody(j);
            assertTrue(note.containsEntry(j));
        }

        assertEquals(500, note.getLength());
    }

    @Test
    void testDeleteBodyByIndexOnce() {
        note.addEntryToBody("");
        assertTrue(note.containsEntry(""));
        assertEquals(1, note.getLength());

        note.deleteBodyByIndex(0);
        assertFalse(note.containsEntry(""));
        assertEquals(0, note.getLength());
    }

    @Test
    void testDeleteBodyByIndexFiveHundred() {
        for (int i = 0; i < 500; i++) {
            String j = Integer.toString(i);
            note.addEntryToBody(j);
        }

        assertTrue(note.containsEntry("484"));
        assertTrue(note.containsEntry("251"));
        assertTrue(note.containsEntry("0"));
        assertEquals(500, note.getLength());

        note.deleteBodyByIndex(484);
        note.deleteBodyByIndex(251);
        note.deleteBodyByIndex(0);

        assertFalse(note.containsEntry("484"));
        assertFalse(note.containsEntry("251"));
        assertFalse(note.containsEntry("0"));
        assertEquals(497, note.getLength());
    }

    @Test
    void testGetEntryByLineNumber() {
        for (int i = 0; i < 500; i++) {
            String j = Integer.toString(i);
            note.addEntryToBody(j);
        }

        assertEquals(note.getEntryWithLineNumber(484), "Line 485: 484");
        assertEquals(note.getEntryWithLineNumber(251), "Line 252: 251");
        assertEquals(note.getEntryWithLineNumber(0), "Line 1: 0");
    }

    @Test
    void testWithinIndex() {
        assertFalse(note.withinIndex(500));
        assertFalse(note.withinIndex(0));

        note.addEntryToBody("");
        assertTrue(note.withinIndex(0));
    }

    @Test
    void testHasTitle() {
        assertTrue(note.hasTitle(""));
    }

    @Test
    void testSetTitle() {
        assertEquals("", note.getTitle());
        note.setTitle("a");
        assertEquals("a", note.getTitle());
    }

}

 */