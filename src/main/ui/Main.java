package ui;

import model.exceptions.NoTitleException;

public class Main {
    public static void main(String[] args) {
        try {
            new MainGUI("a");
        } catch (NoTitleException noTitleException) {
            noTitleException.printStackTrace();
        }
        //new NoteCreationGUI();
    }
}
