/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.a2dam.jamp.logicControllers;


import com.example.a2dam.jamp.dataClasses.Product;
import com.example.a2dam.jamp.exceptions.PasswordNotOkException;
import com.example.a2dam.jamp.exceptions.UserLoginExistException;
import com.example.a2dam.jamp.exceptions.UserNotExistException;
import com.example.a2dam.jamp.logics.ProductLogic;
import com.example.a2dam.jamp.logics.UserLogic;
import com.example.a2dam.jamp.sockets.SocketProduct;
import com.example.a2dam.jamp.sockets.SocketUser;

import java.util.ArrayList;
import java.util.logging.Logger;

import messageuserbean.UserBean;

/**
 * This class implements iLogic interface
 *
 * @author Julen
 * @author Ander
 * @author Markel
 * @author Paula
 */
public class ProductLogicController implements ProductLogic {

    private final SocketProduct socket = new SocketProduct();
    private static final Logger PRODUCTLOGGER = Logger.getLogger("socketProduct");

    public void consumeProduct(ProductBean product) throws algo{
        PRODUCTLOGGER.info("consumeProduct in ProductLogicController");
        socket.consumeProduct(product);
    }

    public ArrayList<ProductBean> findAllProducts(ProductBean product) throws algo{
        PRODUCTLOGGER.info("findAllProducts in ProductLogicController");
        return socket.findAllProducts(product);
    }
}
