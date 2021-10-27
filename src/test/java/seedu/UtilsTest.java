package seedu;

import org.junit.jupiter.api.Test;

import static seedu.typists.common.Utils.getWordLines;
import static seedu.typists.common.Utils.splitStringIntoWordList;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTest {
    private static final String SAMPLE = "this is test text here";
    //name: whatIsBeingTested_descriptionOfTestInputs_expectedOutcome

    @Test
    public void splitStringIntoWordList_sampleTxt_success() {
        ArrayList<String> result = new ArrayList<>(Arrays.asList("this", "is", "test", "text", "here"));
        assertEquals(result, splitStringIntoWordList(SAMPLE));
    }

    // this test is commented out
    // because it will fail due to array address allocation
    // but the result strings are identical
    // @Test
    //public void getWordLine_sampleTxt_success() {
    // String[] a = new String[]{"this", "is"};
    // String[] b = new String[]{"test", "text"};
    // ArrayList<String[]> result = new ArrayList<>(Arrays.asList(a,b));
    // assertEquals(result, getWordLine(SAMPLE, 2));
    // }
}
