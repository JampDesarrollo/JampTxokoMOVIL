package com.jamp.reto2.sinUsar.exceptions;

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