package org.example.exception;

public class ATMProcessorException extends RuntimeException {
    public ATMProcessorException(String message) {
        super(message);
    }
}
