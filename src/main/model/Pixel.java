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
        /*
        g.setColor(colour);
        g.drawOval(x, y, 10, 10);
        g.fillOval(x, y, 50, 50); */

        g.setColor(Color.YELLOW);
        g.fillOval(x, y, 50, 50);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, 50, 50);

        g.drawLine(x + 20, y + 10, x + 20, y + 20);
        g.drawLine(x + 30, y + 10, x + 30, y + 20);

        g.drawArc(x + 15, y + 15, 20, 20, 180, 180);
    }
}
