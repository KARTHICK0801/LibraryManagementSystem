package com.example.demo.exception;
import com.example.demo.exception.BookNotAvailableException;

public class BookNotAvailableException extends RuntimeException {

    public BookNotAvailableException(String message) {
        super(message);
    }
}
