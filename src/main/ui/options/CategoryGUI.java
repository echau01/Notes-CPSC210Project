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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CategoryGUI extends ContainerGUI {
    private JsonSaver jsonSaver;

    private final Category cty;
    private final CategoryContainerGUI ctycGUI;

    private JList ctyPanel;

    // CONSTRUCTOR
    // EFFECTS: creates a new CategoryGUI
    CategoryGUI(Category cty, CategoryContainerGUI ctycGUI) {
        super(cty.getName(), ctycGUI.toolsGUI);
        this.ctycGUI = ctycGUI;
        this.cty = cty;

        addUIElements();
    }

    // MODIFIES: this
    // EFFECTS: initialises and adds the all the ui elements (buttons, text panels etc.) to the main window
    @Override
    protected void addUIElements() {
        ctyPanel = new JList(getAllNoteNames());
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
        button.addActionListener(e -> {
            cty.addNotes(notePane);
            saveCategoryContainer();
            refresh();
        });
        return button;
    }

    // EFFECTS: makes and returns the note delete button
    //          when clicked, deletes selected note
    private JButton createDeleteButton() {
        super.makeButton("Delete Selected");
        button.addActionListener(e -> deleteNote());
        return button;
    }

    // EFFECTS: makes and returns the note creation button
    //          when clicked, performs the note creation operation
    private JButton createNoteButton() {
        super.makeButton("Create New Note");
        button.addActionListener(e -> {
            new NoteCreationGUI();
            saveCategoryContainer();
            noteGUI.dispose();
            dispose();
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


    // EFFECTS: returns a DefaultListModel of all the category names in ctyc
    private DefaultListModel<String> getAllNoteNames() {
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
    @Override
    protected void refresh() {
        new CategoryGUI(cty, ctycGUI);
        dispose();
    }
}
