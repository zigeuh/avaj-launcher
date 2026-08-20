package io.github.zigeuh.avaj.exceptions;

public class InvalidScenarioException extends Exception {
    public InvalidScenarioException(String message) {
        super("Invalid Scenario - " + message);
    }
}
