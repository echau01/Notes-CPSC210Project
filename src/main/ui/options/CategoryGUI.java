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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CategoryGUI extends PopupGUI {
    protected static final int WIDTH = 640;
    protected static final int HEIGHT = 480;
    private static final int DIVIDER_SIZE = 0;

    private static final String DESTINATION = "./data/CategoryContainer.json";
    private JsonSaver jsonSaver;

    private NoteGUI noteGUI;
    private Category cty;
    private CategoryContainer ctyc;
    private NotePanel notePane;

    private JList ctyPanel;

    CategoryGUI(NoteGUI noteGUI, CategoryContainer ctyc, Category cty, NotePanel notePane) {
        super(cty.getName(), WIDTH, HEIGHT);
        jsonSaver = new JsonSaver(DESTINATION);
        this.ctyc = ctyc;
        this.cty = cty;
        this.notePane = notePane;
        this.noteGUI = noteGUI;

        addUIElements();
    }

    @Override
    protected void addUIElements() {
        ctyPanel = new JList(getAllCategoryNames());
        ctyPanel.setBorder(BorderFactory.createTitledBorder("Notes"));
        ctyPanelAddMouseListener();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(createSaveButton());
        buttonPanel.add(createDeleteButton());
        buttonPanel.add(createNoteButton());


        JSplitPane divider = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctyPanel, buttonPanel);
        divider.setDividerLocation(HEIGHT - HEIGHT / 4);
        divider.setDividerSize(DIVIDER_SIZE);
        divider.setEnabled(false);
        add(divider);
    }


    private JButton createSaveButton() {
        super.makeButton("Save");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCategoryContainer();
                refresh();
            }
        });
        return button;
    }

    private JButton createDeleteButton() {
        super.makeButton("Delete Selected");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteNote();
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

    private void deleteNote() {
        String selected = ctyPanel.getSelectedValue().toString();
        cty.removeNotesByName(selected);
        saveCategoryContainer();
        refresh();
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
                        noteGUI.dispose();
                        dispose();
                    } catch (NoTitleException noTitleException) {
                        new ErrorGUI("Error loading the desired note.", "Cannot load note");
                    }
                }
            }
        });
    }

    private void refresh() {
        new CategoryGUI(noteGUI, ctyc, cty, notePane);
        dispose();
    }
}
