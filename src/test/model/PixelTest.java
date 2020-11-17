package model;

import model.exceptions.NoTitleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

public class PixelTest {

    Pixel pixel;

    @BeforeEach
    void testInit() {
        pixel = new Pixel(1, 1, Color.black);
    }

    @Test
    void testEquals() {
        assertTrue(pixel.equals(pixel));
        assertFalse(pixel.equals(null));
        assertFalse(pixel.equals(""));
        assertTrue(pixel.equals(new Pixel(1, 1, Color.gray)));
    }
}
