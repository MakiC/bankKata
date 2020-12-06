package com.mybank.exceptions;

public class DateNotWellFormattedException extends RuntimeException {
    public DateNotWellFormattedException(String s) {
        super(s);
    }
}
