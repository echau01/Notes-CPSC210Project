package ui.options;

import model.exceptions.NoTitleException;
import ui.MainGUI;
import ui.PopupGUI;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RenameGUI extends PopupGUI {
    private MainGUI mainGUI;
    private JTextPane pane;

    // https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
    public RenameGUI(MainGUI mainGUI) {
        super("Rename Note");
        this.mainGUI = mainGUI;
    }

    @Override
    protected void addUIElements() {
        pane = new JTextPane();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        pane.setParagraphAttributes(center, true);
        pane.setBorder(BorderFactory.createTitledBorder("Note Title"));
        pane.setSize(new Dimension(WIDTH / 10, HEIGHT / 4));

        JSplitPane divider = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pane, makeButton());
        divider.setDividerLocation(HEIGHT / 2);
        divider.setDividerSize(5);
        divider.setEnabled(false);
        add(divider);
    }

    private JButton makeButton() {
        makeButton("Set Name");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mainGUI.renameNote(pane.getText());
                    dispose();
                } catch (NoTitleException noTitleException) {
                    createTitleWarning();
                }
            }
        });
        return button;
    }
}
