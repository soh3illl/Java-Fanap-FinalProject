package org.example.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {
        super();
    }

    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException(Throwable cause) {
        super(cause);
    }

    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}