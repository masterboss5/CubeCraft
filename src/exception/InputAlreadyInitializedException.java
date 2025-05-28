package exception;

public class InputAlreadyInitializedException extends RuntimeException {
    public InputAlreadyInitializedException(String input) {
        super(input + " " + "input is already registered");
    }
}
