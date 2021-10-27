package seedu.typists.common;

import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.min;

/**
 * Error rate calculator.
 */
public class Error {
    public int wordLengthDifference(String word, String typed) {
        return Math.abs(word.length() - typed.length());
    }

    public int wordCharacterDifference(String word, String typed) {
        int charDiff = 0;
        for (int i = 0; i < min(word.length(), typed.length()); i++) {
            charDiff += (word.charAt(i) != typed.charAt(i) ? 1 : 0);
        }
        return charDiff + wordLengthDifference(word, typed);
    }

    public double wordErrorRate(String word, String typed) {
        return (double) wordCharacterDifference(word, typed) / word.length();
    }

    public int sentenceLengthDifference(ArrayList<String> content, ArrayList<String> typed) {
        return abs(content.size() - typed.size());
    }

    public double sentenceErrorRate(ArrayList<String> content, ArrayList<String> typed) {
        double temp = 0.0;
        for (int i = 0; i < min(content.size(), typed.size()); i++) {
            temp += wordErrorRate(content.get(i), typed.get(i));
        }
        return temp / (double) min(content.size(), typed.size());
    }

    public int wrongWordCount(ArrayList<String> content, ArrayList<String> typed) {
        int temp = 0;
        for (int i = 0; i < min(content.size(), typed.size()); i++) {
            temp += (content.get(i).equals(typed.get(i)) ? 0 : 1);
        }
        return temp;
    }
}
