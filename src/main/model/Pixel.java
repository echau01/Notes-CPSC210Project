package model;

import java.awt.*;

public class Pixel extends Component {

    private Color colour;
    private int x;
    private int y;

    public Pixel(int x, int y, Color colour) {
        this.x = x;
        this.y = y;
        this.colour = colour;
    }
    public void draw(final Graphics g) {
        g.setColor(colour);
        g.fillOval(x, y, 2, 2);
    }
}
