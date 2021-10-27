package seedu.typists.ui;

import seedu.typists.game.GameRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.util.logging.FileHandler;
import java.util.logging.ConsoleHandler;

import static seedu.typists.common.Messages.SUMMARY;

public class SummaryUi {

    private static final Logger LOGGER = Logger.getLogger(TextUi.class.getName());

    public static void displaySummary(HashMap<String, Object> summary) {
        assert summary.containsKey("timeElapsed");
        assert summary.containsKey("gameMode");
        assert summary.containsKey("wordsPerMinute");
        assert summary.containsKey("errorWordCount");
        assert summary.containsKey("correctWordCount");
        assert summary.containsKey("totalWordCount");
        assert summary.containsKey("errorWordPercentage");
        assert summary.containsKey("correctWordPercentage");
        assert summary.containsKey("errorWords");


        assert summary.get("timeElapsed") instanceof Double;
        assert summary.get("gameMode") instanceof String;
        assert summary.get("wordsPerMinute") instanceof Double;
        printHeader();
        printOverview(
                (Double) summary.get("timeElapsed"),
                (String) summary.get("gameMode"),
                (Double) summary.get("wordsPerMinute"));
        assert summary.get("errorWordCount") instanceof Integer;
        assert summary.get("errorWordPercentage") instanceof Double;
        printErrorStatistics(
                (Integer) summary.get("errorWordCount"),
                (Double) summary.get("errorWordPercentage"),
                (Integer) summary.get("totalWordCount"));
        assert summary.get("correctWordCount") instanceof Integer;
        assert summary.get("correctWordPercentage") instanceof Double;
        printSuccessStatistics(
                (Integer) summary.get("correctWordCount"),
                (Double) summary.get("correctWordPercentage"),
                (Integer) summary.get("totalWordCount"));
        assert summary.get("errorWords") instanceof List;
        printErrorWords((ArrayList<String>) summary.get("errorWords"));
    }

    public static void displaySummary(GameRecord gameRecord) {
        printOverview(gameRecord.getTimeElapsed(),
                gameRecord.getGameMode(),
                gameRecord.getWpm());
        printErrorStatistics(gameRecord.getErrorWordCount(),
                gameRecord.getErrorWordPercentage(),
                gameRecord.getTotalWordCount());
        printSuccessStatistics(gameRecord.getCorrectWordCount(),
                gameRecord.getCorrectWordPercentage(),
                gameRecord.getTotalWordCount());
        printErrorWords(gameRecord.getErrorWords());
    }

    private static void printErrorStatistics(int errorWordCount, double errorWordPercentage, int totalWordCount) {
        System.out.print("Number of Wrong Words: "
                + errorWordCount
                + "/"
                + totalWordCount
                + "|" + String.format("%.2f", errorWordPercentage) + "%\n");
    }

    private static void printSuccessStatistics(int correctWordCount, double correctWordPercentage, int totalWordCount) {
        System.out.print("Number of Correct Words: "
                + correctWordCount
                + "/"
                + totalWordCount
                + "|" + String.format("%.2f", correctWordPercentage) + "%\n");
    }


    static void printErrorWords(ArrayList<String> errorWords) {
        setUpLog();
        assert errorWords != null;
        System.out.print("Mistakes: ");
        if (errorWords.size() == 0) {
            System.out.print("No words typed wrongly.\n");
            return;
        }
        for (int i = 0; i < errorWords.size(); i++) {
            assert errorWords != null;
            if (i % 8 == 0) {
                System.out.print("\n");
            }
            System.out.print(errorWords.get(i).trim());
            if (i != (errorWords.size() - 1)) {
                System.out.print("|");
            }
        }
        System.out.print("\n");
    }

    private static void printOverview(double timeElapsed, String gameMode, double wpm) {
        System.out.print("Game Mode: " + gameMode + '\n');
        System.out.print("WPM: " + String.format("%.2f", wpm) + '\n');
        System.out.print("Total Time taken for the game: " + String.format("%.2f", timeElapsed) + " seconds\n");
    }

    private static void printHeader() {
        System.out.print(SUMMARY + '\n');
    }

    public static void setUpLog() {
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.ALL);

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        LOGGER.addHandler(ch);

        try {
            FileHandler fh = new FileHandler(TextUi.class.getName() + ".log");
            fh.setFormatter(new SimpleFormatter());
            fh.setLevel(Level.FINE);
            LOGGER.addHandler(fh);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "File logger failed to set up\n", e);
        }

        LOGGER.info("Set up log in SummaryUi");
    }


}
