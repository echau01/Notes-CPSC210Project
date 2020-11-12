package ui;

import javax.swing.*;
import java.awt.*;

public abstract class PopupGUI extends JFrame {

    protected JButton button;


    // https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
    public PopupGUI(String name, int x, int y) {
        super(name);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - x / 2, dim.height / 2 - y / 2);

        addUIElements();

        setSize(x, y);
        setResizable(false);
        setVisible(true);
    }

    protected abstract void addUIElements();

    protected void makeButton(String name) {
        button = new JButton(name);
        button.setBorder(BorderFactory.createEmptyBorder());
    }

    // TODO: fix this
    protected void createTitleWarning() {
        JLabel titleWarning = new JLabel("Enter a proper title!");
        titleWarning.setForeground(Color.RED);
        add(titleWarning);
    }
}
