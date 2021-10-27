package seedu.typists.parser;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.typists.exception.InvalidStringInputException;

public class StringParser {

    /**
     * Splits the string based on the separator provided as parameter.
     *
     * @param s         the string to be split
     * @param separator a string representing the separator that you want to split the string on
     * @return ArrayList of strings
     */
    public static ArrayList<String> splitString(String s, String separator) throws InvalidStringInputException {

        if (s == null) {
            throw new InvalidStringInputException();
        }
        ArrayList<String> stringParts = new ArrayList<>();
        stringParts.addAll(Arrays.asList(s.split(separator)));

        return stringParts;
    }

}