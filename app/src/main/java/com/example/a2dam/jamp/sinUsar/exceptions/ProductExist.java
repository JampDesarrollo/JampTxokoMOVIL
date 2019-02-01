package com.example.a2dam.jamp.exceptions;

/**
 * Exception thrown when a Product already exists.
 * @author Julen
 */
public class ProductExist extends Exception {

    public ProductExist(String msg) {
        super(msg);
    }

}