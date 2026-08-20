package io.github.zigeuh.avaj.exceptions;

public class InvalidNumberInputException extends Exception {
    public InvalidNumberInputException(String message) {
        super("Invalid Number Input - " + message);
    }
}
