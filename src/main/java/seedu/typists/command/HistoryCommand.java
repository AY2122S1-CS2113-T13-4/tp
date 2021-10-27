package seedu.typists.command;

import seedu.typists.exception.InvalidCommandException;
import seedu.typists.game.GameRecord;
import seedu.typists.game.GameRecordsManager;
import seedu.typists.ui.HistoryCommandUi;

import java.util.ArrayList;

public class HistoryCommand implements Command {
    private int numberOfRecords = 10;
    private int totalNumberOfRecords;
    private String gameMode;
    private ArrayList<String> args;
    private static final String GAME_MODE_OPTION = "g";
    private static final String NUMBER_OF_RECORDS_OPTION = "n";
    private static final String HELP_OPTION = "h";
    private static GameRecordsManager gameRecordsManager;

    public void run(ArrayList<String> args) {
        this.args = args;
        boolean isHelp = isHelpCommand();
        if (isHelp) {
            HistoryCommandUi.displayHelp();
        } else {
            executeCommand();
        }
    }

    private void executeCommand() {
        gameRecordsManager = GameRecordsManager.getGameRecordsManager();
        try {
            parseCommand();
            ArrayList<GameRecord> gameRecords = gameRecordsManager.getGameRecords(gameMode, numberOfRecords);
            HistoryCommandUi.displayGameRecords(gameRecords, totalNumberOfRecords);
        } catch (InvalidCommandException e) {
            String errorMessage = "Error: " + e.toString().split(":")[1];
            System.out.print(errorMessage);
        }
    }


    private boolean isHelpCommand() {
        int indexHelp = args.indexOf("-" + HELP_OPTION);
        if (indexHelp != -1) {
            return true;
        } else {
            return false;
        }

    }

    private void parseCommand() throws InvalidCommandException {
        getGameMode();
        getNumberOfRecords();
    }

    private void getNumberOfRecords() throws InvalidCommandException {
        int indexNumberOfRecords = args.indexOf("-" + NUMBER_OF_RECORDS_OPTION);
        if (indexNumberOfRecords == -1) {
            totalNumberOfRecords = gameRecordsManager.getNumberOfGameRecords(gameMode);
            numberOfRecords = totalNumberOfRecords;
            return;
        } else {
            if (args.size() < indexNumberOfRecords + 2) {
                throw new InvalidCommandException("Missing argument for number of records option.\n");
            }
            try {
                totalNumberOfRecords = gameRecordsManager.getNumberOfGameRecords(gameMode);
                numberOfRecords = Integer.parseInt(args.get(indexNumberOfRecords + 1));
            } catch (NumberFormatException e) {
                throw new InvalidCommandException("Invalid argument for number of records option.\n"
                        + "Option only accepts integer values."
                        + "Input \"history -h\" for help\n");
            }
        }

    }

    private void getGameMode() throws InvalidCommandException {
        int indexGameMode = args.indexOf("-" + GAME_MODE_OPTION);
        if (indexGameMode == -1) {
            throw new InvalidCommandException("Invalid command.\n"
                    + "Input \"history -h\" for help\n");
        } else {
            if (args.size() < indexGameMode + 2) {
                throw new InvalidCommandException("Missing argument for game mode option.\n");
            }
            gameMode = args.get(indexGameMode + 1);

        }
        if (!gameMode.equals("time") && !gameMode.equals("word")) {
            throw new InvalidCommandException("Invalid argument for game mode option.\n"
                    + "Input \"history -h\" for help\n");
        } else {
            gameMode = gameMode.substring(0, 1).toUpperCase() + gameMode.substring(1) + "-limited";
        }
    }

}
