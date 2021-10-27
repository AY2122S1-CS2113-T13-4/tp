package seedu.typists.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.typists.common.Messages.SUMMARY;

class SummaryUiTest {

    //This code (lines 15 to 38) was referenced from https://www.baeldung.com/java-testing-system-out-println
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void displaySummary_0ErrorWords() {
        double timeElapsed = 10.03221;
        String gameMode = "Word-limited";
        int errorWordCount = 0;
        int correctWordCount = 10;
        int totalWordCount = 0;
        ArrayList<String> errorWords = new ArrayList<>();
        HashMap<String, Object> summary = initializeSummary(
                timeElapsed, gameMode, errorWordCount, correctWordCount, totalWordCount, errorWords
        );
        String expected = SUMMARY + '\n'
                + "Game Mode: " + gameMode + '\n'
                + "WPM: " + String.format("%.2f", (Double) summary.get("wordsPerMinute")) + "\n"
                + "Total Time taken for the game: " + String.format("%.2f", timeElapsed) + " seconds\n"
                + "Number of Wrong Words: " + errorWordCount + "/" + totalWordCount
                + "|" + String.format("%.2f", summary.get("errorWordPercentage")) + "%\n"
                + "Number of Correct Words: "
                + correctWordCount
                + "/"
                + totalWordCount
                + "|" + String.format("%.2f", summary.get("correctWordPercentage")) + "%\n"
                + "Mistakes: No words typed wrongly.\n";

        SummaryUi.displaySummary(summary);
        String actual = outputStreamCaptor.toString();
        assertEquals(expected, actual);

    }

    @Test
    void displaySummary_noUserInput() {
        double timeElapsed = 10.03221;
        String gameMode = "Word-limited";
        int errorWordCount = 10;
        int correctWordCount = 0;
        int totalWordCount = 10;
        ArrayList<String> errorWords = new ArrayList<>(
                Arrays.asList(
                        new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"})
        );
        HashMap<String, Object> summary = initializeSummary(
                timeElapsed, gameMode, errorWordCount, correctWordCount, totalWordCount, errorWords
        );
        String expected = SUMMARY + '\n'
                + "Game Mode: " + gameMode + '\n'
                + "WPM: " + String.format("%.2f", (Double) summary.get("wordsPerMinute")) + "\n"
                + "Total Time taken for the game: " + String.format("%.2f", timeElapsed) + " seconds\n"
                + "Number of Wrong Words: " + errorWordCount + "/" + totalWordCount
                + "|" + String.format("%.2f", summary.get("errorWordPercentage")) + "%\n"
                + "Number of Correct Words: "
                + correctWordCount
                + "/"
                + totalWordCount
                + "|" + String.format("%.2f", summary.get("correctWordPercentage")) + "%\n"
                + "Mistakes: \n"
                + "one|two|three|four|five|six|seven|eight|\n"
                + "nine|ten\n";

        SummaryUi.displaySummary(summary);
        String actual = outputStreamCaptor.toString();
        assertEquals(expected, actual);
    }


    @Test
    void printErrorWords_multipleOfEight() {
        ArrayList<String> errorWords = new ArrayList<>(Arrays.asList(
                "foo1", "foo2", "foo3", "foo4", "foo5", "foo6", "foo7", "foo8",
                "bar1", "bar2", "bar3", "bar4", "bar5", "bar6", "bar7", "bar8"
        ));
        String expected = "Mistakes: \n"
                + "foo1|foo2|foo3|foo4|foo5|foo6|foo7|foo8|\n"
                + "bar1|bar2|bar3|bar4|bar5|bar6|bar7|bar8\n";
        SummaryUi.printErrorWords(errorWords);
        String actual = outputStreamCaptor.toString();
        assertEquals(expected, actual);
    }

    @Test
    void printErrorWords_notMultipleOfEight() {
        ArrayList<String> errorWords = new ArrayList<>(Arrays.asList(
                "foo1", "foo2", "foo3", "foo4", "foo5", "foo6", "foo7", "foo8",
                "bar1", "bar2", "bar3", "bar4", "bar5", "bar6", "bar7"
        ));
        String expected = "Mistakes: \n"
                + "foo1|foo2|foo3|foo4|foo5|foo6|foo7|foo8|\n"
                + "bar1|bar2|bar3|bar4|bar5|bar6|bar7\n";
        SummaryUi.printErrorWords(errorWords);
        String actual = outputStreamCaptor.toString();
        assertEquals(expected, actual);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    HashMap<String, Object> initializeSummary(
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