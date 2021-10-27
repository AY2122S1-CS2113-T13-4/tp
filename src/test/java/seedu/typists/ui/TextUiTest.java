package seedu.typists.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.typists.common.Messages.SUMMARY;

class TextUiTest {
    //This code (lines 15 to 38) was referenced from https://www.baeldung.com/java-testing-system-out-println
    /*private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void showSummary() {
        TextUi textUi = new TextUi();
        String expected = SUMMARY + '\n'
                + "Number of Wrong Words: 5/10\n"
                + "Error Percentage of Wrong Words: 50.00%\n"
                + "Wrong Words:\n"
                + "foo|bar\n"
                + "WPM: 40.08\n"
                + "Total Time taken for the game: 10.07 seconds\n";

        textUi.showSummary(5, 50.00000, java.util.Arrays.asList("foo", "bar"), 40.08, 10, 10.07);
        String actual = outputStreamCaptor.toString();
        assertEquals(expected, actual);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void printErrorWords_noErrorWords_noExceptionThrown() {
        List<String> errorWords = null;
        String expected = "No words typed wrongly.\n";
        TextUi textUi = new TextUi();
        textUi.printErrorWords(errorWords);
        String actual = outputStreamCaptor.toString();
        assertEquals(expected, actual);
    }

    @Test
    void printErrorWords_multipleOfEight() {
        List<String> errorWords = Arrays.asList(
                "foo1", "foo2", "foo3", "foo4", "foo5", "foo6", "foo7", "foo8",
                "bar1", "bar2", "bar3", "bar4", "bar5", "bar6", "bar7", "bar8"
        );
        String expected = "foo1|foo2|foo3|foo4|foo5|foo6|foo7|foo8|\n"
                + "bar1|bar2|bar3|bar4|bar5|bar6|bar7|bar8\n";
        TextUi textUi = new TextUi();
        textUi.printErrorWords(errorWords);
        String actual = outputStreamCaptor.toString();
        assertEquals(expected, actual);
    }

    @Test
    void printErrorWords_notMultipleOfEight() {
        List<String> errorWords = Arrays.asList(
                "foo1", "foo2", "foo3", "foo4", "foo5", "foo6", "foo7", "foo8",
                "bar1", "bar2", "bar3", "bar4", "bar5", "bar6", "bar7"
        );
        String expected = "foo1|foo2|foo3|foo4|foo5|foo6|foo7|foo8|\n"
                + "bar1|bar2|bar3|bar4|bar5|bar6|bar7\n";
        TextUi textUi = new TextUi();
        textUi.printErrorWords(errorWords);
        String actual = outputStreamCaptor.toString();
        assertEquals(expected, actual);
    }*/
}