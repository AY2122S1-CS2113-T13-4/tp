package seedu.typists.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility methods.
 */
public class Utils {
    protected static final Logger logger = Logger.getLogger("Foo");

    /** convert the line of text into an arraylist of word. */
    public static ArrayList<String> splitStringIntoWordList(String line) {
        return new ArrayList<>(Arrays.asList(line.split(" ")));
    }


    /** get one line that is supposed to be displayed at one time of game. **/
    public static String[] getDisplayLines(ArrayList<String> wordLists, int wordsPerLine, int row) {
        int startIndex = (row - 1) * wordsPerLine;
        assert startIndex >= 0 : "word index should be non-negative";
        String[] line = new String[wordsPerLine];
        try {
            for (int i = 0; i < wordsPerLine; i++) {
                line[i] = wordLists.get(startIndex + i);
            }
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "exceed content maximum.");
        }
        return line;
    }

    /** group the word list into groups of x, where x is the number of words per line. */
    public static ArrayList<String[]> getWordLines(String text, int lineLength) {
        ArrayList<String> wordList = splitStringIntoWordList(text);
        ArrayList<String[]> wordLines = new ArrayList<>();
        int j = 0;
        String[] line = new String[lineLength];
        for (String w : wordList) {
            if (j == lineLength - 1) {
                line[j] = w;
                wordLines.add(line.clone());
                j = 0;
            } else {
                line[j] = w;
                j++;
            }
        }
        return wordLines;
    }

    /** same function as getWordLines, but the param is ArrayList not string. */
    public static ArrayList<String[]> getWordLineFromStringArray(List<String> stringList) {
        ArrayList<String[]> wordLines = new ArrayList<>();
        for (String s : stringList) {
            String[] wordLine = s.split(" ");
            wordLines.add(wordLine);
        }
        return wordLines;
    }
}
