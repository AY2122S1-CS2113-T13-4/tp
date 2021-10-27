package seedu;

import org.junit.jupiter.api.Test;
import seedu.typists.content.WikiImport;
import seedu.typists.exception.InvalidArticleException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WikiImportTest {

    // methodBeingTested_inputGiven_expectedOutcome
    // Input, GitHub, relevant article (longer than 0)
    @Test
    void getArticle_GitHub_expectArticle() throws InvalidArticleException {
        String article = "GitHub";
        WikiImport wiki = new WikiImport();
        String content = wiki.getArticle(article);
        assertTrue(content.length() > 0);
    }

    // test failure case
    // method being tested, Githob, expect exception
    @Test
    void getArticle_Githob_expectException() {
        String article = "Githob";
        WikiImport wiki = new WikiImport();
        assertThrows(InvalidArticleException.class, () -> wiki.getArticle(article));
    }
}