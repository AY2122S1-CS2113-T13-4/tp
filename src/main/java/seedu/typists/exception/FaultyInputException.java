package seedu.typists.exception;

public class FaultyInputException extends Exception {
    public FaultyInputException(String errorMessage) {
        super(errorMessage);
    }
}
