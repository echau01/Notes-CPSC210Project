package ui;

import java.util.Scanner;

abstract class UI {

    protected Scanner keyInput;
    protected boolean running;
    protected String cmd;

    // MODIFIES: this
    // EFFECTS: initialises the entire ui
    public void init() {
        keyInput = new Scanner(System.in);
        running = true;

        while (running) {
            consoleUI();
            processCommands();
        }
    }

    // MODIFIES: this
    // EFFECTS: processes key inputs, x terminates the ui
    public void processCommands() {
        cmd = keyInput.next();
        cmd = cmd.toUpperCase();

        if (cmd.equals("X")) {
            running = false;
            System.out.println("Exiting...");
        }
    }

    // EFFECTS: prints out the terminal ui
    public abstract void consoleUI();
}
