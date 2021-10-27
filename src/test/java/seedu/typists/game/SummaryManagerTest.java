package seedu.typists.game;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SummaryManagerTest {

    @Test
    void generateSummary_0ErrorWords() {
        ArrayList<String[]> expectedInput = new ArrayList<>();
        expectedInput.add(new String[]{"one", "two", "three", "four"});
        expectedInput.add(new String[]{"five", "six", "seven", "eight", "nine", "ten"});
        int totalWordCount = expectedInput.get(0).length + expectedInput.get(1).length;
        int correctWordCount = totalWordCount;
        int errorWordCount = 0;
        ArrayList<String> errorWords = new ArrayList<>();
        ArrayList<String[]> actualInput = new ArrayList<>();
        actualInput.addAll(expectedInput);
        double timeElapsed = 10.9021;
        String gameMode = "Time-limited";
        HashMap<String, Object> actual = SummaryManager.generateSummary(
                expectedInput, actualInput, timeElapsed, gameMode
        );
        HashMap<String, Object> expected = initializeExpectedOutput(
                timeElapsed, gameMode, errorWordCount, correctWordCount, totalWordCount, errorWords
        );
        assertEquals(expected, actual);
    }

    @Test
    void generateSummary_oneLineAllErrorWords() {
        ArrayList<String[]> expectedInput = new ArrayList<>();
        expectedInput.add(new String[]{"one", "two", "three", "four"});
        expectedInput.add(new String[]{"five", "six", "seven", "eight", "nine", "ten"});
        ArrayList<String[]> actualInput = new ArrayList<>();
        actualInput.add(expectedInput.get(0));
        actualInput.add(expectedInput.get(0));
        double timeElapsed = 10.9021;
        String gameMode = "Time-limited";
        int totalWordCount = expectedInput.get(0).length + expectedInput.get(1).length;
        int errorWordCount = expectedInput.get(1).length;
        int correctWordCount = totalWordCount - errorWordCount;
        ArrayList<String> errorWords = new ArrayList<>(Arrays.asList(expectedInput.get(1)));
        HashMap<String, Object> actual = SummaryManager.generateSummary(
                expectedInput, actualInput, timeElapsed, gameMode
        );
        HashMap<String, Object> expected = initializeExpectedOutput(
                timeElapsed, gameMode, errorWordCount, correctWordCount, totalWordCount, errorWords
        );
        assertEquals(expected, actual);
    }

    @Test
    void generateSummary_noUserInput() {
        ArrayList<String[]> expectedInput = new ArrayList<>();
        expectedInput.add(new String[]{"one", "two", "three", "four"});
        expectedInput.add(new String[]{"five", "six", "seven", "eight", "nine", "ten"});
        ArrayList<String[]> actualInput = new ArrayList<>();
        double timeElapsed = 10.9021;
        String gameMode = "Time-limited";
        int totalWordCount = expectedInput.get(0).length + expectedInput.get(1).length;
        int errorWordCount = totalWordCount;
        int correctWordCount = 0;
        ArrayList<String> errorWords = new ArrayList<>(Arrays.asList(expectedInput.get(0)));
        errorWords.addAll(Arrays.asList(expectedInput.get(1)));
        HashMap<String, Object> actual = SummaryManager.generateSummary(
                expectedInput, actualInput, timeElapsed, gameMode
        );
        HashMap<String, Object> expected = initializeExpectedOutput(
                timeElapsed, gameMode, errorWordCount, correctWordCount, totalWordCount, errorWords
        );
        assertEquals(expected, actual);

    }

    HashMap<String, Object> initializeExpectedOutput(
            double timeElapsed, String gameMode, int errorWordCount,
            int correctWordCount, int totalWordCount, ArrayList<String> errorWords
    ) {
        HashMap<String, Object> expected = new HashMap<>();
        expected.put("timeElapsed", timeElapsed);
        expected.put("gameMode", gameMode);
        expected.put("wordsPerMinute", (correctWordCount / timeElapsed) * 60);
        expected.put("errorWordCount", errorWordCount);
        expected.put("correctWordCount", correctWordCount);
        expected.put("totalWordCount", totalWordCount);
        expected.put("errorWordPercentage", ((double) errorWordCount / totalWordCount) * 100);
        expected.put("correctWordPercentage", ((double) correctWordCount / totalWordCount) * 100);
        expected.put("errorWords", errorWords);
        return expected;
    }
}