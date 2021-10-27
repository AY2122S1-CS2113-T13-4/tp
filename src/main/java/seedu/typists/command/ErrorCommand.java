package seedu.typists.command;

import seedu.typists.game.ErrorGame;

import java.util.ArrayList;

import static seedu.typists.Main.LINE_LENGTH;
import static seedu.typists.Main.content;

public class ErrorCommand implements Command {
    @Override
    public void run(ArrayList<String> args) {
        startErrorGame();
    }

    public void startErrorGame() {
        new ErrorGame(content.getContent(), LINE_LENGTH);
    }
}
