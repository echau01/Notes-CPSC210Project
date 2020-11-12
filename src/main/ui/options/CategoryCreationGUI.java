package ui.options;

import model.Category;
import model.exceptions.NoTitleException;
import ui.PopupGUI;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryCreationGUI extends PopupGUI {
    private JTextPane pane;
    private OptionsGUI optionsGUI;

    public CategoryCreationGUI(OptionsGUI optionsGUI) {
        super("Category Creation");
        this.optionsGUI = optionsGUI;
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

    protected JButton makeButton() {
        super.makeButton("Create Category");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    optionsGUI.addCategory(new Category(pane.getText()));
                    dispose();
                } catch (NoTitleException noTitleException) {
                    createTitleWarning();
                }
            }
        });
        return button;
    }

    // TODO: fix this
    protected void createTitleWarning() {
        JLabel titleWarning = new JLabel("Enter a proper title!");
        titleWarning.setForeground(Color.RED);
        add(titleWarning);
    }
}
