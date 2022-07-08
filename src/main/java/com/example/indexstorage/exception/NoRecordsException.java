package com.example.indexstorage.exception;

public class NoRecordsException extends RuntimeException {
    public NoRecordsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoRecordsException(String message) {
        super(message);
    }
}
