package by.solution.exceptions;

public class EmptyMapException extends RuntimeException {
    public EmptyMapException() {
        super("model.Set is empty.");
    }
}
