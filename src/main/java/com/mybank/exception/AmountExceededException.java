package com.mybank.exception;
/**
 * @author camara
 */
public class AmountExceededException extends RuntimeException {

    public AmountExceededException(String message){super(message);}
}
