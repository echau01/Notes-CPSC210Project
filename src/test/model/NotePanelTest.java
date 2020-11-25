package model;

import model.exceptions.NoTitleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import static org.junit.jupiter.api.Assertions.*;

class NotePanelTest {

    private NotePanel note;

    @BeforeEach
    void testInit() {
        try {
            note = new NotePanel("name");
            assertEquals("name", note.getTitle());
            assertTrue(note.hasTitle("name"));
        } catch (NoTitleException e) {
            fail("Caught NoTitleException");
        }
    }

    @Test
    void testInitNoTitle() {
        try {
            note = new NotePanel("");
            fail("Expected NoTitleException");
        } catch (NoTitleException e) {
            // Pass
        }
    }

    @Test
    void testSetBody() {
        assertEquals("", note.getBody());
        note.setBody(" ");
        assertEquals(" ", note.getBody());
    }

    @Test
    void testToData() {
        NotePanelData data = note.toData();
        assertEquals("name", data.getTitle());
        assertEquals("", data.getBody());
        assertEquals(0, data.getPixels().size());
    }

    @Test
    void testSetTitle() {
        assertEquals("name", note.getTitle());
        assertTrue(note.hasTitle("name"));
        note.setTitle("a");
        assertEquals("a", note.getTitle());
        assertTrue(note.hasTitle("a"));
    }

    @Test
    void testToggleEditable() {
        note.toggleEditable(false);
        assertFalse(note.isTextEditable());
        note.toggleEditable(true);
        assertTrue(note.isTextEditable());
    }

    @Test
    void testAddPixel() {
        assertEquals(0, note.getPixels().size());
        note.addPixel(new Pixel(1, 1, Color.black));
        assertEquals(1, note.getPixels().size());
    }

    @Test
    void testAddTenPixelsSameLocation() {
        assertEquals(0, note.getPixels().size());
        for (int i = 0; i < 10; i++) {
            note.addPixel(new Pixel(1, 1, Color.black));
        }
        assertEquals(1, note.getPixels().size());
    }

    @Test
    void testAddTenPixelsDifferentLocation() {
        assertEquals(0, note.getPixels().size());
        for (int i = 0; i < 10; i++) {
            note.addPixel(new Pixel(i, i, Color.black));
        }
        assertEquals(10, note.getPixels().size());
    }

    @Test
    void testRemovePixel() {
        assertEquals(0, note.getPixels().size());
        note.addPixel(new Pixel(1, 1, Color.black));
        assertEquals(1, note.getPixels().size());
        note.removePixel(new Pixel(1, 1, Color.black));
        assertEquals(0, note.getPixels().size());
    }

    @Test
    void testRemoveTenPixelsSameLocation() {
        assertEquals(0, note.getPixels().size());
        for (int i = 0; i < 10; i++) {
            note.addPixel(new Pixel(1, 1, Color.black));
        }
        assertEquals(1, note.getPixels().size());
        note.removePixel(new Pixel(1, 1, Color.black));
        assertEquals(0, note.getPixels().size());
    }

    @Test
    void testRemoveTenPixelsDifferentLocation() {
        assertEquals(0, note.getPixels().size());
        for (int i = 0; i < 10; i++) {
            note.addPixel(new Pixel(i, i, Color.black));
        }
        assertEquals(10, note.getPixels().size());
        note.removePixel(new Pixel(0, 0, Color.black));
        note.removePixel(new Pixel(1, 1, Color.black));
        note.removePixel(new Pixel(2, 2, Color.black));
        assertEquals(7, note.getPixels().size());
    }

    @Test
    void testAddMouseListener() {
        MouseMotionListener mml = makeMouseListener();
        note.addMouseMotionListener(mml);
        assertEquals(mml, note.getMouseMotionListener());
    }

    @Test
    void testPaintComponent() {

    }

    // EFFECTS: creates a generic MouseMotionListener
    private MouseMotionListener makeMouseListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }
            @Override
            public void mouseMoved(MouseEvent e) {
            }
        };
    }


}
