package ui;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {

    private static final int WIDTH = 960;
    private static final int HEIGHT = 640;
    private static final int TOOLBAR_SIZE = 70;

    private ButtonGroup colourButtons;
    private ButtonGroup toolButtons;

    private JPanel optionsPanel;
    private JPanel coloursPanel;
    private JPanel toolsPanel;
    private JTextArea textPanel;

    public MainGUI() {
        // give this the title of the notes later - use untitled as a placeholder value
        super("Untitled");

        initPanels();
        generatePanelLayout();
        generateToolsUI();

        setSize(WIDTH, HEIGHT);
        setVisible(true);

    }

    private void generateToolsUI() {
        generateColourButtons();
        generateToolButtons();
        generateOptionButtons();
    }

    private void generateOptionButtons() {
        JButton optionButton = new JButton("Save");
        JButton exitButton = new JButton("Load");
        optionsPanel.add(optionButton);
        optionsPanel.add(exitButton);
    }

    private void generateToolButtons() {
        toolButtons = new ButtonGroup();
        Tools[] tools;
        tools = Tools.values();
        for (Tools t: tools) {
            String buttonName = t.toString().substring(0, 1) + t.toString().substring(1).toLowerCase();
            JRadioButton tb = new JRadioButton(buttonName);
            toolButtons.add(tb);
            toolsPanel.add(tb);
        }
    }

    private void generateColourButtons() {
        colourButtons = new ButtonGroup();
        Colours[] colours;
        colours = Colours.values();
        for (Colours c: colours) {
            String buttonName = c.toString().substring(0, 1) + c.toString().substring(1).toLowerCase();
            JRadioButton cb = new JRadioButton(buttonName);
            colourButtons.add(cb);
            coloursPanel.add(cb);
        }
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

        textPanel = new JTextArea();
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

        toolsDivider = new JSplitPane(JSplitPane.VERTICAL_SPLIT, toolTypeDivider, textPanel);
        toolsDivider.setDividerLocation(TOOLBAR_SIZE);
        toolsDivider.setDividerSize(5);
        toolsDivider.setEnabled(false);

        add(toolsDivider);
    }

}
