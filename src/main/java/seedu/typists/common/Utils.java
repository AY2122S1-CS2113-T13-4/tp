package seedu.typists.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility methods.
 */
public class Utils {

    /** convert the line of text into an arraylist of word. */
    public static ArrayList<String> splitStringIntoWordList(String line) {
        return new ArrayList<>(Arrays.asList(line.split(" ")));
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
