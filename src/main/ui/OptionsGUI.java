package ui;

import model.NoTitleException;
import model.NotePanel;
import persistence.JsonParser;
import persistence.JsonSaver;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsGUI extends JFrame {
    private static final int WIDTH = 320;
    private static final int HEIGHT = 240;

    private NotePanel notePane;
    private JsonSaver jsonSaver;
    private JsonParser jsonLoader;

    OptionsGUI(NotePanel notePane) {
        super("Note Creation");

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - WIDTH / 2, dim.height / 2 - HEIGHT / 2);
        this.notePane = notePane;

        addUIElements();

        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setVisible(true);
    }

    private void addUIElements() {


    }

    private JButton createButton() {
        JButton button = new JButton("Create Note");
        button.setBorder(BorderFactory.createEmptyBorder());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        return button;
    }

    // TODO: fix this
    private void createTitleWarning() {
        JLabel titleWarning = new JLabel("Enter a proper title!");
        titleWarning.setForeground(Color.RED);
        add(titleWarning);
    }
}
