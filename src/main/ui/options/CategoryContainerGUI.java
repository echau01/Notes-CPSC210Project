package ui.options;

import model.Category;
import model.NotePanel;
import model.exceptions.NoCategoryException;
import model.exceptions.NoTitleException;
import persistence.JsonParser;
import persistence.JsonSaver;
import ui.ErrorGUI;
import ui.NoteGUI;
import ui.PopupGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// TODO: every single time this is loaded, it will use persistence to load the categories and notes
public class CategoryContainerGUI extends PopupGUI {
    protected static final int WIDTH = 640;
    protected static final int HEIGHT = 480;
    private static final int DIVIDER_SIZE = 0;

    private static final String DESTINATION = "./data/CategoryContainer.json";
    private JsonParser jsonParser;
    private JsonSaver jsonSaver;

    private NoteGUI noteGUI;
    private NotePanel notePane;
    private model.CategoryContainer ctyc;

    private JList ctycPanel;

    public CategoryContainerGUI(NoteGUI noteGUI, NotePanel notePane) {
        super("Note Creation", WIDTH, HEIGHT);
        this.notePane = notePane;
        this.noteGUI = noteGUI;

        loadCategoryContainer();
        addUIElements();
    }

    // https://stackoverflow.com/questions/4262669/refresh-jlist-in-a-jframe/4262716
    public void addCategory(Category c) {
        ctyc.addCategory(c);
        saveCategoryContainer();
        refresh();
    }

    public void renameCategory(String name) throws NoTitleException {
        if (name.length() == 0) {
            throw new NoTitleException();
        }
        String selected = ctycPanel.getSelectedValue().toString();
        Category selectedCty = ctyc.getCategoryByName(selected);
        selectedCty.setName(name);
        saveCategoryContainer();
        refresh();
    }

    // https://stackoverflow.com/questions/4344682/double-click-event-on-jlist-element
    @Override
    protected void addUIElements() {
        ctycPanel = new JList(getAllCategoryNames());
        ctycPanel.setBorder(BorderFactory.createTitledBorder("Categories"));
        ctycPanelAddMouseListener();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(createRenameButton());
        buttonPanel.add(createDeleteButton());
        buttonPanel.add(createCategoryButton());

        JSplitPane divider = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctycPanel, buttonPanel);
        divider.setDividerLocation(HEIGHT - HEIGHT / 4);
        divider.setDividerSize(DIVIDER_SIZE);
        divider.setEnabled(false);
        add(divider);
    }

    private JButton createRenameButton() {
        super.makeButton("Rename Selected");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createRenameCategoryGUI();
            }
        });
        return button;
    }

    private JButton createDeleteButton() {
        super.makeButton("Delete Selected");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCategory();
            }

            private void deleteCategory() {
                String selected = ctycPanel.getSelectedValue().toString();
                ctyc.removeCategoryByName(selected);
                saveCategoryContainer();
                refresh();
            }
        });
        return button;
    }

    private JButton createCategoryButton() {
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

    private void createRenameCategoryGUI() {
        try {
            String selected = ctycPanel.getSelectedValue().toString();
            new CategoryRenameGUI(this);
            saveCategoryContainer();
        } catch (NullPointerException e) {
            new ErrorGUI("Please select a Category.", "Error");
        }
    }

    private void saveCategoryContainer() {
        jsonSaver = new JsonSaver(DESTINATION);
        try {
            jsonSaver.save(ctyc);
        } catch (Exception e) {
            new ErrorGUI("Error in saving files.", "Cannot save file.");
            // e.printStackTrace();
        }
    }

    private void loadCategoryContainer() {
        jsonParser = new JsonParser(DESTINATION);
        try {
            ctyc = jsonParser.parseFile();
        } catch (Exception e) {
            new ErrorGUI("Error in loading saved files.", "Cannot load from file.");
            ctyc = new model.CategoryContainer();
            // e.printStackTrace();
        }
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
                    new CategoryGUI(noteGUI, ctyc, c, notePane);
                    dispose();
                }
            }
        });
    }

    private DefaultListModel<String> getAllCategoryNames() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String name : ctyc.getCategories().keySet()) {
            model.addElement(name);
        }
        return model;
    }

    private void refresh() {
        new CategoryContainerGUI(noteGUI, notePane);
        dispose();
    }
}
