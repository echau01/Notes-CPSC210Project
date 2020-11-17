package model;

import org.json.JSONObject;
import persistence.Writable;
import java.awt.*;
import java.util.Objects;

public class Pixel extends Component implements Writable {

    private final Color colour;
    private final int mouseX;
    private final int mouseY;

    // CONSTRUCTOR
    // EFFECTS: constructs a new Pixel with the given x, y and colour
    public Pixel(int x, int y, Color colour) {
        this.mouseX = x;
        this.mouseY = y;
        this.colour = colour;
    }

    // EFFECTS: draws the pixel to x and y
    public void draw(final Graphics g) {
        g.setColor(colour);
        g.fillOval(mouseX, mouseY, 5, 5);
    }

    // EFFECTS: converts this into a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x", mouseX);
        jsonObject.put("y", mouseY);
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
        return mouseX == pixel.mouseX
                && mouseY == pixel.mouseY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mouseX, mouseY);
    }
}
