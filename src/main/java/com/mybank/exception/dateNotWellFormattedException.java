package com.mybank.service;

import java.text.ParseException;

public class dateNotWellFormattedException extends Throwable {
    public dateNotWellFormattedException(String s, ParseException e) {
        super(s);
    }
}
