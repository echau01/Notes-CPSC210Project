package ui;

import model.Category;
import model.exceptions.NoTitleException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class PopupGUI extends JFrame {

    protected static final int WIDTH = 320;
    protected static final int HEIGHT = 240;
    protected JButton button;

    // https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
    public PopupGUI(String name) {
        super(name);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - WIDTH / 2, dim.height / 2 - HEIGHT / 2);

        addUIElements();

        setSize(WIDTH, HEIGHT);
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
