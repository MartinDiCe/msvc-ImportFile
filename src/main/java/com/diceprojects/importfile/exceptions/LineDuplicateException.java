package com.diceprojects.importfile.exceptions;

public class LineDuplicateException extends RuntimeException {

    public LineDuplicateException() {
        super();
    }

    public LineDuplicateException(String message) {
        super(message);
    }

    public LineDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public LineDuplicateException(Throwable cause) {
        super(cause);
    }
}
