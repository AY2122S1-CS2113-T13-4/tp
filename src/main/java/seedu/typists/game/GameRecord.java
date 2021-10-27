package seedu.typists.game;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameRecord {

    private String gameMode;
    private double timeElapsed;
    private int errorWordCount;
    private int correctWordCount;
    private int totalWordCount;
    private double errorWordPercentage;
    private double correctWordPercentage;
    private double wpm;
    private ArrayList<String> errorWords;

    public GameRecord(
            String gameMode, double timeElapsed, int errorWordCount, int correctWordCount, int totalWordCount,
            double errorWordPercentage, double correctWordPercentage, double wpm, ArrayList<String> errorWords
    ) {

        this.gameMode = gameMode;
        this.timeElapsed = timeElapsed;
        this.errorWordCount = errorWordCount;
        this.correctWordCount = correctWordCount;
        this.totalWordCount = totalWordCount;
        this.errorWordPercentage = errorWordPercentage;
        this.correctWordPercentage = correctWordPercentage;
        this.wpm = wpm;
        this.errorWords = errorWords;
    }

    public String getGameMode() {

        return gameMode;
    }


    public double getTimeElapsed() {
        return timeElapsed;
    }

    public int getErrorWordCount() {

        return errorWordCount;
    }

    public int getCorrectWordCount() {

        return correctWordCount;
    }

    public int getTotalWordCount() {

        return totalWordCount;
    }

    public double getErrorWordPercentage() {

        return errorWordPercentage;
    }

    public double getCorrectWordPercentage() {

        return correctWordPercentage;
    }

    public double getWpm() {

        return wpm;
    }

    public ArrayList<String> getErrorWords() {
        return errorWords;
    }

    public String getStringFormat() {
        String separator = "|";
        String formatted = gameMode
                + separator
                + timeElapsed
                + separator
                + errorWordCount
                + separator
                + correctWordCount
                + separator
                + totalWordCount
                + separator
                + errorWordPercentage
                + separator
                + correctWordPercentage
                + separator
                + wpm
                + separator
                + errorWords.toString();
        return formatted;

    }
}
