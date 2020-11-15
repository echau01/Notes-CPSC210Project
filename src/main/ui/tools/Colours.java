package ui.tools;

import java.awt.*;

// https://www.baeldung.com/java-enum-values
public enum Colours {
    BLACK(Color.BLACK),
    GRAY(Color.GRAY),
    RED(Color.RED),
    BLUE(Color.BLUE),
    YELLOW(Color.YELLOW),
    GREEN(Color.GREEN);

    public final Color colour;

    Colours(Color colour) {
        this.colour = colour;
    }

    public Color getColour() {
        return colour;
    }
}
