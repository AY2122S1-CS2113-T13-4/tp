package seedu.typists.ui;

import org.junit.jupiter.api.Test;
import seedu.typists.exception.InvalidStringInputException;
import seedu.typists.game.WordLimitDataProcessor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordLimitDataProcessorTest {

    @Test
    void getError_expect1() throws InvalidStringInputException {
        String a = "Equal Or Not";
        String b = "Equal Or Not?";
        WordLimitDataProcessor c =  new WordLimitDataProcessor(a, b);
        assertEquals(1,c.getError());
    }
}
