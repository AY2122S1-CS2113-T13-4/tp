package seedu.typists.command;

import seedu.typists.game.GameRecordsManager;

import java.util.ArrayList;

import static seedu.typists.Main.uiBot;

public class ExitCommand implements Command {
    @Override
    public void run(ArrayList<String> args) {
        System.out.println("Updating game records...");
        GameRecordsManager gameRecordsManager = GameRecordsManager.getGameRecordsManager();
        gameRecordsManager.updateGameRecords();
        uiBot.showBye();
    }
}
