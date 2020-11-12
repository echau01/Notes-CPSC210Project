package ui;

import model.NotePanel;
import ui.options.OptionsGUI;
import ui.options.RenameGUI;
import ui.tools.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolsGUI {
    private JPanel optionsPanel;
    private JPanel coloursPanel;
    private JPanel toolsPanel;
    private NotePanel notePane;
    private MainGUI mainGUI;

    private ButtonGroup colourButtons;
    private ButtonGroup toolButtons;

    private Color selectedColour;

    private PenTool penTool;
    private EraserTool eraserTool;
    private TextTool textTool;

    ToolsGUI(MainGUI mainGUI, JPanel optionsPanel, JPanel coloursPanel, JPanel toolsPanel, NotePanel notePane) {
        this.optionsPanel = optionsPanel;
        this.coloursPanel = coloursPanel;
        this.toolsPanel = toolsPanel;
        this.notePane = notePane;
        this.mainGUI = mainGUI;

        selectedColour = Color.black;

        generateColourButtons();
        generateToolButtons();
        generateOptionButtons();
        initTools();
    }

    private void initTools() {
        penTool = new PenTool(notePane, selectedColour);
        eraserTool = new EraserTool(notePane);
        textTool = new TextTool(notePane, selectedColour);
    }

    private void generateOptionButtons() {
        JButton optionsButton = new JButton("Options");
        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OptionsGUI(notePane);
            }
        });
        JButton renameButton = new JButton("Rename Note");
        renameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RenameGUI(mainGUI);
            }
        });
        optionsPanel.add(optionsButton);
        optionsPanel.add(renameButton);
    }


    // https://stackoverflow.com/questions/17653116/action-listener-for-multiple-radio-buttons
    private void generateToolButtons() {
        toolButtons = new ButtonGroup();
        Tools[] tools;
        tools = Tools.values();

        for (Tools t: tools) {
            String buttonName = t.toString().substring(0, 1) + t.toString().substring(1).toLowerCase();
            JRadioButton tb = new JRadioButton(buttonName);
            tb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    performToolAction(t);
                }
            });
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
            cb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedColour = c.getColour();
                    penTool.setColour(selectedColour);
                }
            });
            colourButtons.add(cb);
            coloursPanel.add(cb);
        }
    }

    // TODO: handle the case where the colour / tool selected is null later
    private void performToolAction(Tools t) {
        penTool.setActive(false);
        eraserTool.setActive(false);
        textTool.setActive(false);
        switch (t) {
            case PEN:
                penTool.setActive(true);
                break;
            case ERASER:
                eraserTool.setActive(true);
                break;
            case TEXT:
                textTool.setActive(true);
                break;
            default:
                ;
                break;
        }
    }

}
