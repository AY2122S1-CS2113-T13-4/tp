package seedu.typists.game;

import seedu.typists.ui.TextUi;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.typists.common.Utils.getWordLines;

public class ErrorGame extends Game {
    private final TextUi ui;

    protected ArrayList<String> inputLines;
    protected final ArrayList<String[]> wordLines;

    public ErrorGame(String targetWordSet, int wordsPerLine) {
        ui = new TextUi();
        inputLines = new ArrayList<>();
        wordLines = getWordLines(targetWordSet, wordsPerLine);
        runGame();
    }

    public boolean readyToStartGame() {
        Scanner in = new Scanner(System.in);
        String command = "";
        while (!command.equals("yes")) {
            ui.printScreen("Do you want to start now?");
            command = in.nextLine();
        }
        return true;
    }

    public void runGame() {
        if (readyToStartGame()) {
            for (int i = 0; i < wordLines.size(); i++) {
                ui.printLine(wordLines.get(i));
            }
        }
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < wordLines.size(); i++) {
            String userInput = in.nextLine();
            inputLines.add(userInput);
            i++;
        }
        System.out.println("Game Finished.");
    }
}
