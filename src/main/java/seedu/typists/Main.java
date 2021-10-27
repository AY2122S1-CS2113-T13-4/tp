package seedu.typists;

import seedu.typists.content.Content;
import seedu.typists.command.Command;
import seedu.typists.command.CommandFactory;
import seedu.typists.ui.TextUi;

import seedu.typists.storage.Storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    /**
     * Version info of the program.
     */
    public static final String VERSION = "Typist - Version 2.0";
    public static final int LINE_LENGTH = 10;
    public static Content content;
    public static TextUi uiBot;
    Storage storage;


    public Main() {
        uiBot = new TextUi();
        this.storage = new Storage();
        content = new Content();
    }

    public void start() {
        uiBot.showWelcomeMessage(VERSION);
    }

    public String read() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void runCommandLoop() {
        CommandFactory cmdFactory = new CommandFactory();
        String command;
        ArrayList<String> args;
        do {
            String[] input = read().split(" ");
            command = input[0];
            args = new ArrayList<String>(Arrays.asList(Arrays.copyOfRange(input, 1, input.length)));
            Command c = cmdFactory.getCommand(input[0]);
            if (c != null) {
                c.run(args);
            }
        } while (!command.equals("bye"));
    }

    public void exit() {
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        start();
        runCommandLoop();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Main().run();
    }
}