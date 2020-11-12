package ui;

import model.NotePanel;
import model.exceptions.NoTitleException;

public class Main {
    public static void main(String[] args) {
        try {
            new NoteGUI(new NotePanel("E"));
        } catch (NoTitleException noTitleException) {
            noTitleException.printStackTrace();
        }
        //new NoteCreationGUI();
    }
}
