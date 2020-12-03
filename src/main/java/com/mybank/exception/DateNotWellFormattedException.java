package com.mybank.exception;

import java.text.ParseException;

public class DateNotWellFormattedException extends RuntimeException {
    public DateNotWellFormattedException(String s, ParseException e) {
        super(s);
    }
}
