package seedu.typists.exception;

public class InvalidArticleException extends Exception {
    @Override
    public String getMessage() {
        return "Invalid article given";
    }
}
