package com.example.demo.exception;
import com.example.demo.exception.BorrowLimitExceededException;

public class BorrowLimitExceededException extends RuntimeException {

    public BorrowLimitExceededException(String message) {
        super(message);
    }
}
