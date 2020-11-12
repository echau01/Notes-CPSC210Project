package ui.options;

import model.Category;
import model.CategoryContainer;
import model.NotePanel;
import model.exceptions.NoTitleException;
import persistence.JsonSaver;
import ui.ErrorGUI;
import ui.NoteCreationGUI;
import ui.NoteGUI;
import ui.PopupGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CategoryGUI extends PopupGUI {
    protected static final int WIDTH = 640;
    protected static final int HEIGHT = 480;

    private static final String DESTINATION = "./data/CategoryContainer.json";
    private JsonSaver jsonSaver;

    private Category cty;
    private CategoryContainer ctyc;
    private NotePanel notePane;

    private JList ctyPanel;

    CategoryGUI(CategoryContainer ctyc, Category cty, NotePanel notePane) {
        super(cty.getName(), WIDTH, HEIGHT);
        jsonSaver = new JsonSaver(DESTINATION);
        this.ctyc = ctyc;
        this.cty = cty;
        this.notePane = notePane;

        addUIElements();
    }

    @Override
    protected void addUIElements() {
        ctyPanel = new JList(getAllCategoryNames());
        ctyPanelAddMouseListener();

        JSplitPane buttonDivider = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createSaveButton(), createNoteButton());
        buttonDivider.setDividerLocation(WIDTH / 2);
        buttonDivider.setDividerSize(5);
        buttonDivider.setEnabled(false);

        JSplitPane divider = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctyPanel, buttonDivider);
        divider.setDividerLocation(HEIGHT - HEIGHT / 4);
        divider.setDividerSize(5);
        divider.setEnabled(false);
        add(divider);
    }

    private void saveCategoryContainer() {
        cty.addNotes(notePane);
        jsonSaver = new JsonSaver(DESTINATION);
        try {
            jsonSaver.save(ctyc);
        } catch (Exception e) {
            new ErrorGUI("Error in saving files.", "Cannot save file.");
            // e.printStackTrace();
        }
    }

    private DefaultListModel<String> getAllCategoryNames() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (NotePanel notes: cty.getNotesOnly()) {
            model.addElement(notes.getTitle());
        }
        return model;
    }

    private JButton createSaveButton() {
        super.makeButton("Save");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCategoryContainer();
                ctyPanel.setModel(getAllCategoryNames());
            }
        });
        return button;
    }

    private JButton createNoteButton() {
        super.makeButton("Create New Note");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NoteCreationGUI();
                saveCategoryContainer();
                dispose();
            }
        });
        return button;
    }

    private void ctyPanelAddMouseListener() {
        ctyPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = (JList) e.getSource();
                // Double-click detected
                if (e.getClickCount() == 2) {
                    String key = list.getSelectedValue().toString();
                    NotePanel selectedNotePanel = cty.getNotes().get(key);
                    try {
                        new NoteGUI(selectedNotePanel);
                        saveCategoryContainer();
                        dispose();
                    } catch (NoTitleException noTitleException) {
                        new ErrorGUI("Error loading the desired note.", "Cannot load note");
                    }
                }
            }
        });
    }
}
