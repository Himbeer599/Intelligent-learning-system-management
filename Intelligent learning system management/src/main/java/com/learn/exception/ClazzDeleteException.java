package com.learn.exception;

public class ClazzDeleteException extends RuntimeException{
    private static final String DEFAULT_MESSAGE = "Sorry, students are enrolled in this class, and it cannot be deleted directly.";

    public ClazzDeleteException() {
        super(DEFAULT_MESSAGE);
    }

    public ClazzDeleteException(String message) {
        super(message);
    }
}
