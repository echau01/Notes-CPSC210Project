package model;

import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;
import java.util.Objects;

public class Pixel extends Component implements Writable {

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

    public Color getColour() {
        return colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pixel pixel = (Pixel) o;
        return x == pixel.x &&
                y == pixel.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x", x);
        jsonObject.put("y", y);
        jsonObject.put("colour", colour.getRGB());
        return jsonObject;
    }
}
