package com.mybank.exception;

import java.text.ParseException;

public class dateNotWellFormattedException extends RuntimeException {
    public dateNotWellFormattedException(String s, ParseException e) {
        super(s);
    }
}
