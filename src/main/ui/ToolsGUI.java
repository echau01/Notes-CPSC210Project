package ui;

import model.NotePanel;
import model.Notes;
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

    private ButtonGroup colourButtons;
    private ButtonGroup toolButtons;

    private Color selectedColour;
    private Tools selectedTool;

    Notes note;

    ToolsGUI(Notes note, JPanel optionsPanel, JPanel coloursPanel, JPanel toolsPanel, NotePanel notePane) {
        this.optionsPanel = optionsPanel;
        this.coloursPanel = coloursPanel;
        this.toolsPanel = toolsPanel;
        this.notePane = notePane;
        this.note = note;

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
                    performToolAction(selectedTool);
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
                new PenTool(note, notePane, selectedColour);
                break;
            case ERASER:
                break;
            case TEXT:
                new TextTool(note, notePane, selectedColour);
                break;
            default:
                ;
                break;
        }
    }

}
