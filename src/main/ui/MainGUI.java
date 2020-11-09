package ui;

import model.NoTitleException;
import model.Notes;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 790;
    private static final int TOOLBAR_SIZE = 70;

    private JPanel optionsPanel;
    private JPanel coloursPanel;
    private JPanel toolsPanel;
    private JTextPane notePane;

    private Notes note;

    public MainGUI(String title) throws NoTitleException {
        // give this the title of the notes later - use untitled as a placeholder value
        super(title);
        note = new Notes(title);

        if (title.length() == 0) {
            dispose();
            throw new NoTitleException();
        }

        initPanels();
        generatePanelLayout();
        new ToolsGUI(note, optionsPanel, coloursPanel, toolsPanel, notePane);

        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setVisible(true);
    }

    private void initPanels() {
        toolsPanel = new JPanel();
        toolsPanel.setLayout(new GridLayout(2, 6));
        toolsPanel.setBorder(BorderFactory.createTitledBorder("Tools"));

        optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(2, 2));

        coloursPanel = new JPanel();
        coloursPanel.setLayout(new GridLayout(2, 6));
        coloursPanel.setBorder(BorderFactory.createTitledBorder("Colours"));

        notePane = new JTextPane();
        notePane.setEditable(false);
    }

    // MODIFIES: this
    // EFFECTS: splits the gui into many different JPanels which JComponents can be added to
    private void generatePanelLayout() {
        JSplitPane toolsDivider;
        JSplitPane toolTypeDivider;
        JSplitPane optionsDivider;

        optionsDivider = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, optionsPanel, toolsPanel);
        optionsDivider.setDividerLocation(WIDTH / 6);
        optionsDivider.setDividerSize(5);
        optionsDivider.setEnabled(false);

        toolTypeDivider = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, optionsDivider, coloursPanel);
        toolTypeDivider.setDividerLocation(WIDTH / 2);
        toolTypeDivider.setDividerSize(5);
        toolTypeDivider.setEnabled(false);

        toolsDivider = new JSplitPane(JSplitPane.VERTICAL_SPLIT, toolTypeDivider, notePane);
        toolsDivider.setDividerLocation(TOOLBAR_SIZE);
        toolsDivider.setDividerSize(5);
        toolsDivider.setEnabled(false);

        add(toolsDivider);
    }

}
