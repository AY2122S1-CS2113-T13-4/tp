package seedu.typists.content;

import org.fastily.jwiki.core.Wiki;
import seedu.typists.exception.InvalidArticleException;

/**
 * Imports the first paragraph of a text from Wikipedia.
 */
public class WikiImport {
    public String getArticle(String article) throws InvalidArticleException {
        Wiki wiki = new Wiki.Builder().build();
        if (!wiki.exists(article)) {
            throw new InvalidArticleException();
        }
        String temp = wiki.getTextExtract(article);
        if (temp.length() < 1) {
            throw new InvalidArticleException();
        }
        assert temp.length() > 1;
        return temp;
    }
}
