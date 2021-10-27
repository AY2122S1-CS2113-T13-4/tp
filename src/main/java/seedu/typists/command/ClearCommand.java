package seedu.typists.command;

import seedu.typists.exception.InvalidCommandException;
import seedu.typists.game.GameRecordsManager;
import seedu.typists.ui.ClearCommandUi;

import java.util.ArrayList;

public class ClearCommand implements Command {
    private int numberOfRecords = 0;
    private String gameMode;
    private ArrayList<String> args;
    GameRecordsManager gameRecordsManager;
    private static final String HELP_OPTION = "h";
    private static final String GAME_MODE_OPTION = "g";

    public void run(ArrayList<String> args) {
        this.args = args;
        boolean isHelp = isHelpCommand();
        if (isHelp) {
            ClearCommandUi.displayHelp();
        } else {
            executeCommand();
        }
    }

    public void executeCommand() {
        gameRecordsManager = GameRecordsManager.getGameRecordsManager();
        try {
            parseCommand();
            gameRecordsManager.clearAllRecords(gameMode);
            ClearCommandUi.displaySuccess(gameMode);
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
    }

    private void getGameMode() throws InvalidCommandException {
        int indexGameMode = args.indexOf("-" + GAME_MODE_OPTION);
        if (indexGameMode == -1) {
            gameMode = "all";
        } else {
            if (args.size() < indexGameMode + 2) {
                throw new InvalidCommandException("Missing argument for game mode option.\n");
            }
            gameMode = args.get(indexGameMode + 1);

        }
        if (!gameMode.equals("time") && !gameMode.equals("word") && !gameMode.equals("all")) {
            throw new InvalidCommandException("Invalid argument for game mode option.\n"
                    + "Input \"history -h\" for help\n");
        } else {
            if (gameMode.equals("all")) {
                gameMode = "all";
            } else {
                gameMode = gameMode.substring(0, 1).toUpperCase() + gameMode.substring(1) + "-limited";
            }
        }
    }

}
