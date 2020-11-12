package ui;

import model.exceptions.NoTitleException;
import model.NotePanel;

import javax.swing.*;
import java.awt.*;

public class NoteGUI extends JFrame {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 790;
    private static final int TOOLBAR_SIZE = 70;
    private static final int DIVIDER_SIZE = 0;
    private static final Color BACKGROUND = new Color(240, 240, 240);

    private JPanel optionsPanel;
    private JPanel coloursPanel;
    private JPanel toolsPanel;
    private NotePanel notePane;

    public NoteGUI(NotePanel notePane) throws NoTitleException {
        // give this the title of the notes later - use untitled as a placeholder value
        super(notePane.getTitle());
        this.notePane = notePane;

        // throws a NoTitleException if the note title has no characters
        if (notePane.getTitle().length() == 0) {
            dispose();
            throw new NoTitleException();
        }

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - WIDTH / 2, dim.height / 2 - HEIGHT / 2);

        initPanels();
        generatePanelLayout();
        new ToolsGUI(this, optionsPanel, coloursPanel, toolsPanel, notePane);

        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setVisible(true);
    }

    public void dispose() {
        removeAll();
        setVisible(false);
    }

    public void renameNote(String name) throws NoTitleException {
        if (name.length() == 0) {
            throw new NoTitleException();
        } else {
            setTitle(name);
            notePane.setTitle(name);
        }
    }

    private void initPanels() {
        toolsPanel = new JPanel();
        toolsPanel.setLayout(new GridLayout(2, 6));
        toolsPanel.setBorder(BorderFactory.createTitledBorder("Tools"));
        toolsPanel.setBackground(BACKGROUND);

        optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(2, 2));
        optionsPanel.setBackground(BACKGROUND);

        coloursPanel = new JPanel();
        coloursPanel.setLayout(new GridLayout(2, 6));
        coloursPanel.setBorder(BorderFactory.createTitledBorder("Colours"));
        coloursPanel.setBackground(BACKGROUND);
    }

    // MODIFIES: this
    // EFFECTS: splits the gui into many different JPanels which JComponents can be added to
    private void generatePanelLayout() {
        JSplitPane toolsDivider;
        JSplitPane toolTypeDivider;
        JSplitPane optionsDivider;

        optionsDivider = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, optionsPanel, toolsPanel);
        optionsDivider.setDividerLocation(WIDTH / 6);
        optionsDivider.setDividerSize(DIVIDER_SIZE);
        optionsDivider.setEnabled(false);

        toolTypeDivider = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, optionsDivider, coloursPanel);
        toolTypeDivider.setDividerLocation(WIDTH / 2);
        toolTypeDivider.setDividerSize(DIVIDER_SIZE);
        toolTypeDivider.setEnabled(false);

        toolsDivider = new JSplitPane(JSplitPane.VERTICAL_SPLIT, toolTypeDivider, notePane);
        toolsDivider.setDividerLocation(TOOLBAR_SIZE);
        toolsDivider.setDividerSize(DIVIDER_SIZE);
        toolsDivider.setEnabled(false);

        add(toolsDivider);
    }
}
