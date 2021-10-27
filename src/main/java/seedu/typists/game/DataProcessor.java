package seedu.typists.game;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.typists.Main.LINE_LENGTH;
import static seedu.typists.common.Utils.getWordLineFromStringArray;

public class DataProcessor {
    private static final Logger logger = Logger.getLogger("Foo");
    private final List<String[]> checkerWordLines;
    private final List<String[]> userWordLines;
    public final double totalTime;
    private final List<String> errorWords;

    public DataProcessor(TimeModeGame tg) {
        this.checkerWordLines = tg.wordLines;
        this.userWordLines = tg.userLines;
        this.totalTime = tg.realGameTime;
        this.errorWords = getErrorWords();
    }

    /** Get the list of words that the user made mistakes on. */
    public List<String> getErrorWords() {
        List<String> errorWords = new ArrayList<>();
        int i = 0;
        for (String[] sa : userWordLines) {
            String[] checker = checkerWordLines.get(i);
            int ii = 0;
            for (String s : checker) {
                try {
                    if (!s.equals(sa[ii])) {
                        errorWords.add(s);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    logger.log(Level.INFO, "word(s) missed during input.");
                }
                ii++;
            }
            i++;
        }
        return errorWords;
    }

    /** Get the total number of wrong words typed.*/
    public int getErrorWordCount() {
        return errorWords.size();
    }

    public int getTotalWordTyped() {
        return userWordLines.size() * LINE_LENGTH;
    }

    public double getWordPerMinute() {
        return getTotalWordTyped() / (totalTime / 60);
    }

    public void getWrongKeyStrokes(ArrayList<String> checkerText, ArrayList<String> userText) {
        int totalErrorCount = 0;
        for (int i = 0; i < userText.size(); i++) {
            totalErrorCount += getLineWrongKeyStrokes(checkerText.get(i), userText.get(i));
        }
        System.out.println("Total number of wrong keystrokes: " + totalErrorCount);
    }

    public int getLineWrongKeyStrokes(String checker, String answer) {
        int errorCount = 0;
        for (int i = 0; i < checker.length(); i++) {
            try {
                if (checker.charAt(i) != answer.charAt(i)) {
                    errorCount++;
                }
            } catch (StringIndexOutOfBoundsException e) {
                errorCount++;
            }
        }
        return errorCount;
    }

    public double getErrorPercentage() {
        return (double) getErrorWordCount() / (double) getTotalWordTyped();
    }
}