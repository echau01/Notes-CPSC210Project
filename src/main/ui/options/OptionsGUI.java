package ui.options;

import jdk.nashorn.internal.parser.JSONParser;
import model.Category;
import model.CategoryContainer;
import model.NotePanel;
import model.exceptions.NoTitleException;
import persistence.JsonParser;
import ui.ErrorGUI;
import ui.PopupGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

// TODO: every single time this is loaded, it will use persistence to load the categories and notes
public class OptionsGUI extends PopupGUI {
    protected static final int WIDTH = 640;
    protected static final int HEIGHT = 480;

    private static final String DESTINATION = "./data/CategoryContainer.json";
    private JsonParser jsonParser;

    private NotePanel notePane;
    private CategoryContainer ctyc;

    private JList ctycPanel;

    public OptionsGUI(NotePanel notePane) {
        super("Note Creation", WIDTH, HEIGHT);
        this.notePane = notePane;

        loadCategoryContainer();
        addUIElements();
    }

    // https://stackoverflow.com/questions/4262669/refresh-jlist-in-a-jframe/4262716
    public void addCategory(Category c) {
        ctyc.addCategory(c);
        ctycPanel.setModel(getAllCategoryNames());
    }

    // https://stackoverflow.com/questions/4344682/double-click-event-on-jlist-element
    @Override
    protected void addUIElements() {
        ctycPanel = new JList(getAllCategoryNames());
        ctycPanelAddMouseListener();

        JSplitPane divider = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctycPanel, createButton());
        divider.setDividerLocation(HEIGHT - HEIGHT / 4);
        divider.setDividerSize(5);
        divider.setEnabled(false);
        add(divider);
    }

    private void loadCategoryContainer() {
        jsonParser = new JsonParser(DESTINATION);
        try {
            ctyc = jsonParser.parseFile();
        } catch (Exception e) {
            new ErrorGUI("Error in loading saved files.", "Cannot load from file.");
            ctyc = new CategoryContainer();
            // e.printStackTrace();
        }
    }

    private DefaultListModel<String> getAllCategoryNames() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String name : ctyc.getCategories().keySet()) {
            model.addElement(name);
        }
        return model;
    }

    private JButton createButton() {
        super.makeButton("New Category");
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
                    new CategoryGUI(ctyc, c, notePane);
                    dispose();
                }
            }
        });
    }

}
