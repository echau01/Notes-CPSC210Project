package ui.tools;

import model.Notes;

import java.awt.*;

public class Ink {

    private int x;
    private int y;
    private Notes note;
    private Color colour;

    Ink(Notes note, Color colour, int x, int y) {
        this.x = x;
        this.y = y;
        this.colour = colour;
        this.note = note;
    }
}
