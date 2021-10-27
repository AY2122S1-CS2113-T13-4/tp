package seedu.typists.game;

import seedu.typists.exception.InvalidStringInputException;

import java.util.ArrayList;

import static seedu.typists.parser.StringParser.splitString;

public class WordLimitDataProcessor {
    protected String userInput;
    protected String contentWords;
    protected Boolean isExit;

    /**
     * Ture if the 2 inputs are different.
     *
     * @param input one of inputs
     * @param text  another input
     */
    public WordLimitDataProcessor(String input, String text) {
        this.userInput = input;
        this.contentWords = text;
        this.isExit = !input.equals(text);
    }

    /**
     * Get exit.
     *
     * @return obtain the Exit status
     */
    public Boolean getIsExit() {
        return isExit;
    }

    public int getError() throws InvalidStringInputException {
        ArrayList<String> userInput0 = splitString(userInput, " ");
        ArrayList<String> contentWords0 = splitString(contentWords, " ");
        ArrayList<String> list3 = new ArrayList<String>();

        for (int i = 0; i < contentWords0.size(); i++) {
            for (int j = 0; j < userInput0.size(); j++) {
                if (contentWords0.contains(userInput0.get(j))) {
                    if (contentWords0.get(i).equals(userInput0.get(j))) {
                        list3.add(contentWords0.get(i));
                    }
                } else {
                    break;
                }
            }
        }
        return contentWords0.size() - list3.size();
    }
}
