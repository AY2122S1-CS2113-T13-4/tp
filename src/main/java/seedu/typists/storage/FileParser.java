package seedu.typists.storage;

import seedu.typists.game.GameRecord;

import java.util.ArrayList;
import java.util.Arrays;

public class FileParser {

    public static GameRecord convertStringToGameRecord(String gameRecordString) {
        String[] gameRecordStats = gameRecordString.split("\\|");
        assert gameRecordStats.length >= 8;
        String gameMode = getGameMode(gameRecordStats);
        double timeElapsed = getTimeElapsed(gameRecordStats);
        int errorWordCount = getErrorWordCount(gameRecordStats);
        int correctWordCount = getCorrectWordCount(gameRecordStats);
        int totalWordCount = getTotalWordCount(gameRecordStats);
        double errorWordPercentage = getErrorWordPercentage(gameRecordStats);
        double correctWordPercentage = getCorrectWordPercentage(gameRecordStats);
        double wpm = getWpm(gameRecordStats);
        ArrayList<String> errorWords = getErrorWords(gameRecordStats);

        GameRecord gameRecord = new GameRecord(
                gameMode,
                timeElapsed,
                errorWordCount,
                correctWordCount,
                totalWordCount,
                errorWordPercentage,
                correctWordPercentage,
                wpm,
                errorWords
        );
        return gameRecord;
    }

    private static String getGameMode(String[] gameRecordStats) {
        return gameRecordStats[0];
    }

    private static double getTimeElapsed(String[] gameRecordStats) {
        return Double.parseDouble(gameRecordStats[1]);
    }

    private static int getErrorWordCount(String[] errorWordCountString) {
        return Integer.parseInt(errorWordCountString[2]);
    }

    private static int getCorrectWordCount(String[] gameRecordStats) {
        return Integer.parseInt(gameRecordStats[3]);
    }

    private static int getTotalWordCount(String[] gameRecordStats) {
        return Integer.parseInt(gameRecordStats[4]);
    }

    private static double getErrorWordPercentage(String[] gameRecordStats) {
        return Double.parseDouble(gameRecordStats[5]);
    }

    private static double getCorrectWordPercentage(String[] gameRecordStats) {
        return Double.parseDouble(gameRecordStats[6]);
    }

    private static double getWpm(String[] wpmString) {
        return Double.parseDouble(wpmString[7]);
    }

    private static ArrayList<String> getErrorWords(String[] gameRecordStats) {
        if (gameRecordStats[8].trim().equals("[]")) {
            return new ArrayList<>();
        }
        String[] errorWords = gameRecordStats[8].substring(1, gameRecordStats[8].length() - 1).split(",");
        return new ArrayList<>(Arrays.asList(errorWords));
    }
}
