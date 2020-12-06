package com.mybank.exceptions;

public class TransactionInvalidException extends RuntimeException {
    public TransactionInvalidException(String s) {
        super(s);
    }
}
