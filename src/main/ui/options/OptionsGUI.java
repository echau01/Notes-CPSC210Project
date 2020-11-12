package ui.options;

import model.Category;
import model.CategoryContainer;
import model.NotePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// TODO: every single time this is loaded, it will use persistence to load the categories and notes
public class OptionsGUI extends JFrame {
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;

    private NotePanel notePane;
    private JList ctycPanel;
    private CategoryContainer ctyc;

    public OptionsGUI(NotePanel notePane) {
        super("Note Creation");
        ctyc = new CategoryContainer();
        this.notePane = notePane;

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - WIDTH / 2, dim.height / 2 - HEIGHT / 2);

        addUIElements();

        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setVisible(true);
    }

    // https://stackoverflow.com/questions/4262669/refresh-jlist-in-a-jframe/4262716
    public void addCategory(Category c) {
        ctyc.addCategory(c);
        ctycPanel.setModel(getAllCategoryNames());
    }

    private DefaultListModel<String> getAllCategoryNames() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String name : ctyc.getCategories().keySet()) {
            model.addElement(name);
        }
        return model;
    }

    // https://stackoverflow.com/questions/4344682/double-click-event-on-jlist-element
    private void addUIElements() {
        ctycPanel = new JList(getAllCategoryNames());
        ctycPanelAddMouseListener();

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

    private void ctycPanelAddMouseListener() {
        ctycPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = (JList) e.getSource();
                // Double-click detected
                if (e.getClickCount() == 2) {
                    String key = list.getSelectedValue().toString();
                    Category c = ctyc.getCategories().get(key);
                    new CategoryGUI(c);
                    dispose();
                }
            }
        });
    }

}
