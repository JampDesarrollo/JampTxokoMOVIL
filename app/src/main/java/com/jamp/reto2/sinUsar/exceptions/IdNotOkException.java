package com.jamp.reto2.sinUsar.exceptions;

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