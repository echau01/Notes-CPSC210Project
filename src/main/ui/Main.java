package ui;

import model.NoTitleException;

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
