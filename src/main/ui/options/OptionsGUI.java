package ui.options;

import model.Category;
import model.CategoryContainer;
import model.NotePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsGUI extends JFrame {
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;

    private NotePanel notePane;
    private JList ctycPanel;
    private CategoryContainer ctyc;

    public OptionsGUI(NotePanel notePane) {
        super("Note Creation");
        ctyc = new CategoryContainer();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - WIDTH / 2, dim.height / 2 - HEIGHT / 2);
        this.notePane = notePane;

        addUIElements();

        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setVisible(true);
    }

    // https://stackoverflow.com/questions/4262669/refresh-jlist-in-a-jframe/4262716
    public void addCategory(Category c) {
        ctyc.addCategory(c);
        ctycPanel.setModel(categoryContainerToListModel());
    }

    private DefaultListModel<Category> categoryContainerToListModel() {
        DefaultListModel<Category> model = new DefaultListModel<>();
        for (Category c : ctyc.getCategories()) {
            model.addElement(c);
        }
        return model;
    }

    private void addUIElements() {
        ctycPanel = new JList(ctyc.getCategories().toArray());

        JSplitPane divider = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctycPanel, createButton());
        divider.setDividerLocation(HEIGHT - HEIGHT / 4);
        divider.setDividerSize(5);
        divider.setEnabled(false);
        add(divider);
    }

    private JButton createButton() {
        JButton button = new JButton("New Category");
        button.setBorder(BorderFactory.createEmptyBorder());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeCategoryCreationUI();
            }
        });
        return button;
    }

    private void makeCategoryCreationUI() {
        new CategoryCreationGUI(this);
    }

}
