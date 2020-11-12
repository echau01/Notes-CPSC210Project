package ui;

import javax.swing.*;
import java.awt.*;

public abstract class PopupGUI extends JFrame {
    protected Color background = new Color(240, 240, 240);
    protected JButton button;

    // https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
    public PopupGUI(String name, int x, int y) {
        super(name);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - x / 2, dim.height / 2 - y / 2);

        setSize(x, y);
        setResizable(false);
        setVisible(true);
    }

    protected abstract void addUIElements();

    protected void makeButton(String name) {
        button = new JButton(name);
        button.setBackground(background);
        button.setBorder(BorderFactory.createEmptyBorder());
    }

    protected void createTitleWarning() {
        new ErrorGUI("Please enter a valid title.", "Invalid Title");
    }
}
