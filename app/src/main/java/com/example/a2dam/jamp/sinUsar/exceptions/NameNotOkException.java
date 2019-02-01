package com.example.a2dam.jamp.exceptions;

/**
 *Class for the exception
 * @author paula
 */
public class NameNotOkException extends Exception {
    public NameNotOkException() {
    }

    /**
     *Exception with a message
     * @param msg the detail message.
     */
    public NameNotOkException(String msg) {
        super(msg);
    }

}