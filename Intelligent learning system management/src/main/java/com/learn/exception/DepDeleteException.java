package com.learn.exception;

public class DepDeleteException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Sorry, there're still employees in this department. You cannot delete it directly.";

    public DepDeleteException() {
        super(DEFAULT_MESSAGE);
    }

    public DepDeleteException(String message) {
        super(message);
    }
}

