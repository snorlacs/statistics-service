package com.statistics.exception;

public class NoTransactionsMadeException extends RuntimeException {
    public NoTransactionsMadeException(String message) {
        super(message);
    }
}
