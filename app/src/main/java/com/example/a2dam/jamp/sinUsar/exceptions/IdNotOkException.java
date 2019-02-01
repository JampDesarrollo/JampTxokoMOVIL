package com.example.a2dam.jamp.exceptions;

/**
 * Class for the exception
 * @author paula
 */
public class IdNotOkException extends Exception {
    public IdNotOkException() {
    }

    /**
     *Exception with a message
     * @param msg the detail message.
     */
    public IdNotOkException(String msg) {
        super(msg);
    }

}