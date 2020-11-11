package model;

import java.awt.*;
import java.util.Objects;

public class Pixel extends Component {

    private Color colour;
    private int x;
    private int y;

    public Pixel(int x, int y, Color colour) {
        this.x = x;
        this.y = y;
        this.colour = colour;
    }

    // TODO: set a colour
    public void draw(final Graphics g) {
        g.setColor(colour);
        g.fillOval(x, y, 5, 5);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pixel pixel = (Pixel) o;
        return x == pixel.x &&
                y == pixel.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
