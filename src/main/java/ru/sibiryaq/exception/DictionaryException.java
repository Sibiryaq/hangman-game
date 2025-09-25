package ru.sibiryaq.exception;

public class DictionaryException extends RuntimeException {
    public DictionaryException(String msg) {
        super(msg);
    }

    public DictionaryException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
