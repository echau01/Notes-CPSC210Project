package model;

import org.json.JSONObject;
import persistence.Writable;
import java.awt.*;
import java.util.Objects;

public class Pixel extends Component implements Writable {

    private Color colour;
    private int x;
    private int y;

    // CONSTRUCTOR
    // EFFECTS: constructs a new Pixel with the given x, y and colour
    public Pixel(int x, int y, Color colour) {
        this.x = x;
        this.y = y;
        this.colour = colour;
    }

    // EFFECTS: draws the pixel to x and y
    public void draw(final Graphics g) {
        g.setColor(colour);
        g.fillOval(x, y, 5, 5);
    }

    // EFFECTS: returns the colour of the pixel
    public Color getColour() {
        return colour;
    }

    // EFFECTS: converts this into a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x", x);
        jsonObject.put("y", y);
        jsonObject.put("colour", colour.getRGB());
        return jsonObject;
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
}
