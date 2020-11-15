package ui.options;

import model.Category;
import model.CategoryContainer;
import model.NotePanel;
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

    // CONSTRUCTOR
    // EFFECTS: creates a new CategoryGUI
    CategoryGUI(NoteGUI noteGUI, CategoryContainer ctyc, Category cty, NotePanel notePane) {
        super(cty.getName(), WIDTH, HEIGHT);
        jsonSaver = new JsonSaver(DESTINATION);
        this.ctyc = ctyc;
        this.cty = cty;
        this.notePane = notePane;
        this.noteGUI = noteGUI;

        addUIElements();
    }

    // MODIFIES: this
    // EFFECTS: initialises and adds the all the ui elements (buttons, text panels etc.) to the main window
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


    // EFFECTS: makes and returns the save button
    //          when clicked, adds note to category performs save operation
    private JButton createSaveButton() {
        super.makeButton("Save");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cty.addNotes(notePane);
                saveCategoryContainer();
                refresh();
            }
        });
        return button;
    }

    // EFFECTS: makes and returns the note delete button
    //          when clicked, deletes selected note
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

    // EFFECTS: makes and returns the note creation button
    //          when clicked, performs the note creation operation
    private JButton createNoteButton() {
        super.makeButton("Create New Note");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NoteCreationGUI();
                saveCategoryContainer();
                noteGUI.dispose();
                dispose();
            }
        });
        return button;
    }

    // MODIFIES: this
    // EFFECTS: deletes the selected note and saves ctyc, then refreshes ui
    private void deleteNote() {
        try {
            String selected = ctyPanel.getSelectedValue().toString();
            cty.removeNotesByName(selected);
            saveCategoryContainer();
            refresh();
        } catch (NullPointerException e) {
            new ErrorGUI("Please select a Note.", "Error");
        }
    }

    // MODIFIES: this
    // EFFECTS: saves the ctyc to file
    private void saveCategoryContainer() {
        jsonSaver = new JsonSaver(DESTINATION);
        try {
            jsonSaver.save(ctyc);
        } catch (Exception e) {
            new ErrorGUI("Error in saving files.", "Cannot save file.");
            // e.printStackTrace();
        }
    }

    // EFFECTS: returns a DefaultListModel of all the category names in ctyc
    private DefaultListModel<String> getAllCategoryNames() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (NotePanel notes: cty.getNotesOnly()) {
            model.addElement(notes.getTitle());
        }
        return model;
    }

    // EFFECTS: adds a mouse listener to ctyPanel - the mouse listener will check for double clicks on selected items
    //          creates a new noteGUI with the selected notePanel if item is double clicked
    private void ctyPanelAddMouseListener() {
        ctyPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = (JList) e.getSource();
                // Double-click detected
                if (e.getClickCount() == 2) {
                    String key = list.getSelectedValue().toString();
                    NotePanel selectedNotePanel = cty.getNotes().get(key);
                    new NoteGUI(selectedNotePanel);
                    noteGUI.dispose();
                    dispose();
                }
            }
        });
    }

    // EFFECTS: refreshes the ui
    private void refresh() {
        new CategoryGUI(noteGUI, ctyc, cty, notePane);
        dispose();
    }
}
