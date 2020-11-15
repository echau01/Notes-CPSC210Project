package model;

import model.exceptions.NoTitleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesTest {

    private NotePanel note;

    @BeforeEach
    void testInit() {
        try {
            note = new NotePanel("");
        } catch (NoTitleException e) {
            e.printStackTrace();
        }
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
