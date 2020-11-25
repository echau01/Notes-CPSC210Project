package ui.options;

import model.NotePanel;
import ui.NoteGUI;
import ui.options.creation.NoteCreationGUI;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CategoryGUI extends ContainerGUI {
    private final CategoryContainerGUI ctycGUI;

    // CONSTRUCTOR
    // EFFECTS: creates a new CategoryGUI
    CategoryGUI(CategoryContainerGUI ctycGUI) {
        super(ctycGUI.getCty().getName(), ctycGUI.toolsGUI);
        cty = ctycGUI.getCty();
        this.ctycGUI = ctycGUI;

        addUIElements();
    }

    // MODIFIES: this
    // EFFECTS: deletes the selected note and saves ctyc, then refreshes ui
    private void deleteNote() {
        try {
            String selected = objPanel.getSelectedValue().toString();
            cty.removeNotesByName(selected);
            saveCategoryContainer();
            refresh();
        } catch (NullPointerException e) {
            new ErrorGUI("Please select a Note.", "Error");
        }
    }


    // EFFECTS: returns a DefaultListModel of all the category names in ctyc
    @Override
    protected DefaultListModel<String> getAllObjectNames() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (NotePanel notes: cty.getNotesOnly()) {
            model.addElement(notes.getTitle());
        }
        return model;
    }

    // EFFECTS: adds a mouse listener to ctyPanel - the mouse listener will check for double clicks on selected items
    //          creates a new noteGUI with the selected notePanel if item is double clicked
    @Override
    protected void objPanelAddMouseListener() {
        objPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = (JList) e.getSource();
                // Double-click detected
                if (e.getClickCount() == 2) {
                    String key = list.getSelectedValue().toString();
                    NotePanel selectedNotePanel = cty.getNotes().get(key);
                    new NoteGUI(selectedNotePanel);
                    toolsGUI.getNoteGUI().dispose();
                    dispose();
                }
            }
        });
    }

    // EFFECTS: makes and returns the save button
    //          when clicked, adds note to category performs save operation
    @Override
    protected JButton createFirstButton() {
        super.makeButton("Save");
        button.addActionListener(e -> {
            cty.addNotes(toolsGUI.getNoteGUI().getNotePane());
            saveCategoryContainer();
            refresh();
        });
        return button;
    }

    // EFFECTS: makes and returns the note delete button
    //          when clicked, deletes selected note
    @Override
    protected JButton createDeleteButton() {
        super.makeButton("Delete Selected");
        button.addActionListener(e -> deleteNote());
        return button;
    }

    // EFFECTS: makes and returns the note creation button
    //          when clicked, performs the note creation operation
    @Override
    protected JButton createCreationButton() {
        super.makeButton("Create New Note");
        button.addActionListener(e -> {
            new NoteCreationGUI();
            saveCategoryContainer();
            toolsGUI.getNoteGUI().dispose();
            dispose();
        });
        return button;
    }

    // MODIFIES: this
    // EFFECTS: saves the ctyc to file
    @Override
    protected void saveCategoryContainer() {
        ctyc.addCategory(cty);
        super.saveCategoryContainer();
    }

    // EFFECTS: refreshes the ui
    @Override
    protected void refresh() {
        new CategoryGUI(ctycGUI);
        dispose();
    }
}
