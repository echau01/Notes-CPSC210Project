package ui.options;

import javax.swing.*;
import java.awt.*;

public abstract class OptionsGUI extends JFrame {
    protected Color background = new Color(240, 240, 240);
    protected JButton button;

    // https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
    // CONSTRUCTOR
    // EFFECTS: creates a new PopupGUI
    public OptionsGUI(String name, int x, int y) {
        super(name);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - x / 2, dim.height / 2 - y / 2);

        setSize(x, y);
        setResizable(false);
        setVisible(true);
    }

    // EFFECTS: makes a new JButton with the given name and an empty border
    protected void makeButton(String name) {
        button = new JButton(name);
        button.setBackground(background);
        button.setBorder(BorderFactory.createEmptyBorder());
    }

    // EFFECTS: creates a new title warning
    protected void createTitleWarning() {
        new ErrorGUI("Please enter a valid title.", "Invalid Title");
    }
}
