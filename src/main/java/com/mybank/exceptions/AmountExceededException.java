package com.mybank.exceptions;
/**
 * @author camara
 */
public class AmountExceededException extends RuntimeException {

    public AmountExceededException(String message){super(message);}
}
