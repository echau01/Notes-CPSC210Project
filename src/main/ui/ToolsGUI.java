package ui;

import model.NotePanel;
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
    private Tools selectedTool;

    private PenTool penTool;

    ToolsGUI(MainGUI mainGUI, JPanel optionsPanel, JPanel coloursPanel, JPanel toolsPanel, NotePanel notePane) {
        this.optionsPanel = optionsPanel;
        this.coloursPanel = coloursPanel;
        this.toolsPanel = toolsPanel;
        this.notePane = notePane;
        this.mainGUI = mainGUI;

        generateColourButtons();
        generateToolButtons();
        generateOptionButtons();
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
                    selectedTool = t;
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
        notePane.toggleEditable(false);
        switch (t) {
            case PEN:
                penTool = new PenTool(notePane, selectedColour);
                break;
            case ERASER:
                new EraserTool(notePane);
                break;
            case TEXT:
                new TextTool(notePane, selectedColour);
                break;
            default:
                ;
                break;
        }
    }

}
