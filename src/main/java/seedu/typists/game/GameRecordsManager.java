package seedu.typists.game;

import seedu.typists.storage.Storage;

import java.util.ArrayList;
import java.util.HashMap;

public class GameRecordsManager {

    private static GameRecordsManager instance = null;
    private ArrayList<GameRecord> timeLimitedGameRecords;
    private ArrayList<GameRecord> wordLimitedGameRecords;

    private GameRecordsManager() {
        // get gameRecords using some file reader.
        timeLimitedGameRecords = Storage.readGameRecords("Time-limited");
        wordLimitedGameRecords = Storage.readGameRecords("Word-limited");
    }

    public void updateGameRecords() {
        Storage.writeGameRecords(timeLimitedGameRecords, "Time-limited");
        Storage.writeGameRecords(wordLimitedGameRecords, "Word-limited");
    }

    public static GameRecordsManager getGameRecordsManager() {
        if (instance == null) {
            instance = new GameRecordsManager();
        }
        return instance;
    }

    public void addGameRecord(HashMap<String, Object> gameSummary) {
        GameRecord newGameRecord = createGameRecord(gameSummary);
        addRecordToArray(newGameRecord);
        writeRecordToFile(newGameRecord);
    }

    private GameRecord createGameRecord(HashMap<String, Object> gameSummary) {
        String gameMode = (String) gameSummary.get("gameMode");
        double timeElapsed = (Double) gameSummary.get("timeElapsed");
        double wpm = (Double) gameSummary.get("wordsPerMinute");
        int errorWordCount = (Integer) gameSummary.get("errorWordCount");
        int correctWordCount = (Integer) gameSummary.get("correctWordCount");
        int totalWordCount = (Integer) gameSummary.get("totalWordCount");
        double errorWordPercentage = (Double) gameSummary.get("errorWordPercentage");
        double correctWordPercentage = (Double) gameSummary.get("correctWordPercentage");
        ArrayList<String> errorWords = (ArrayList<String>) gameSummary.get("errorWords");

        GameRecord newGameRecord = new GameRecord(
                gameMode, timeElapsed, errorWordCount, correctWordCount, totalWordCount,
                errorWordPercentage, correctWordPercentage, wpm, errorWords
        );
        return newGameRecord;
    }

    private void addRecordToArray(GameRecord gameRecord) {
        String gameMode = gameRecord.getGameMode();
        if (gameMode.equals("Word-limited")) {
            wordLimitedGameRecords.add(gameRecord);
        } else {
            timeLimitedGameRecords.add(gameRecord);
        }
    }


    public ArrayList<GameRecord> getGameRecords(String gameMode, int numberOfRecords) {

        assert ((gameMode.equals("Time-limited")) || (gameMode.equals("Word-limited")));
        if (gameMode.equals("Time-limited")) {
            if (numberOfRecords > timeLimitedGameRecords.size()) {
                return null;
            }
            return new ArrayList<>(timeLimitedGameRecords.subList(
                    timeLimitedGameRecords.size() - numberOfRecords,
                    timeLimitedGameRecords.size()
            ));
        } else {
            if (numberOfRecords > wordLimitedGameRecords.size()) {
                return null;
            }
            return new ArrayList<>(wordLimitedGameRecords.subList(
                    wordLimitedGameRecords.size() - numberOfRecords,
                    wordLimitedGameRecords.size()
            ));
        }
    }

    private void writeRecordToFile(GameRecord gameRecord) {
        String gameMode = gameRecord.getGameMode();
        if (gameMode.equals("Word-limited")) {
            Storage.writeGameRecords(wordLimitedGameRecords, "Word-limited");
        } else {
            Storage.writeGameRecords(timeLimitedGameRecords, "Time-limited");

        }
    }

    public int getNumberOfGameRecords(String gameMode) {
        if (gameMode.equals("Word-limited")) {
            return wordLimitedGameRecords.size();
        } else {
            return timeLimitedGameRecords.size();
        }
    }

    public void clearAllRecords(String gameMode) {
        assert (gameMode.equals("Time-limited") || gameMode.equals("Word-limited") || gameMode.equals("all"));
        if (gameMode.equals("Word-limited")) {
            wordLimitedGameRecords.clear();
        } else if (gameMode.equals("Time-limited")) {
            timeLimitedGameRecords.clear();
        } else {
            wordLimitedGameRecords.clear();
            timeLimitedGameRecords.clear();
        }
    }
}
