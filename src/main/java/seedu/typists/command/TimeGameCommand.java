package seedu.typists.command;

import seedu.typists.game.DataProcessor;
import seedu.typists.game.TimeModeGame;

import java.util.ArrayList;
import java.util.Arrays;

import static seedu.typists.Main.content;
import static seedu.typists.Main.uiBot;
import static seedu.typists.Main.LINE_LENGTH;


public class TimeGameCommand implements Command {

    @Override
    public void run(ArrayList<String> args) {
        TimeModeGame game = createGame();
        game.runGame();
        process(game);
        printUserInput(game);
    }

    public TimeModeGame createGame() {
        return new TimeModeGame(content.getContent(), LINE_LENGTH);
    }

    public void process(TimeModeGame tg) {
        DataProcessor dp = new DataProcessor(tg);
    }

    public void printUserInput(TimeModeGame tg) {
        uiBot.printScreen("user input is: ");
        for (String[] sa : tg.userLines) {
            uiBot.printScreen(Arrays.toString(sa));
        }
    }

}
