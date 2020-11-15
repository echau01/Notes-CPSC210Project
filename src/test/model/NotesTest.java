package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesTest {

    private NotePanel note;

    @BeforeEach
    void testInit() {
        note = new NotePanel("");
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
