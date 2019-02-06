package com.jamp.reto2.sinUsar.logicControllers;

import com.example.a2dam.jamp.rest.ProductRESTClient;
import com.jamp.reto2.dataClasses.ProductBean;
import com.jamp.reto2.sinUsar.exceptions.BusinessLogicException;
import com.jamp.reto2.sinUsar.logic.ProductLogic;
import com.jamp.reto2.sinUsar.rest.ProductRESTClient;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;

/**
 * Class that implements the productLogic iterface
 *
 * @author Julen
 */

public class ProductLogicController implements ProductLogic {

    //REST users web client
    private final ProductRESTClient ProductWebClient;

    /**
     * Attribute to appear the information text.
     */
    private static final Logger LOGGER
            = Logger.getLogger("jamp.pc.logic.IlogicImplementationProduct");

    /**
     * Create a ILogicImplementationroduct.
     */
    public ProductLogicController(){
        ProductWebClient = new ProductRESTClient();
    }

    /**
     * This method returns a all products.
     * @return A collection of Product.
     * @throws BusinessLogicException
     */
    @Override
    public ArrayList<ProductBean> findAllProducts() throws BusinessLogicException {
        ArrayList<ProductBean> productos = null;
        try{
            LOGGER.info("ProductImplementation: Finding all product from REST service (XML).");
            //Ask webClient for all departments' data.
            productos = ProductWebClient.findAllProducts(new GenericType<ArrayList<ProductBean>>() {});
        }catch(ClientErrorException ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception finding all products, {0}",
                    ex.getMessage());
            throw new BusinessLogicException("ProductImplementation: Error finding products:\n"+ex.getMessage());
        }

        return productos;
    }

    /**
     * This method returns a product by id and by txoko.
     * @param idProduct
     * @param idTxoko
     * @return A product
     * @throws BusinessLogicException
     */
    @Override
    public ProductBean findProductByIdByTxoko(String idProduct, String idTxoko) throws BusinessLogicException {
        ProductBean producto = null;
        try{
            LOGGER.info("ProductImplementation: Finding products by id and txoko from REST service (XML).");
            //Ask webClient for all departments' data.
            producto = ProductWebClient.findProductByIdByTxoko(ProductBean.class, idProduct, idTxoko);
        }catch(ClientErrorException ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception finding products by id and txoko, {0}",
                    ex.getMessage());
            throw new BusinessLogicException("ProductImplementation: Error updating user:\n"+ex.getMessage());
        }
        return producto;
    }

    /**
     * This method returns all products by the txoko.
     * @param idTxoko
     * @return A product
     * @throws BusinessLogicException
     */
    @Override
    public List<ProductBean> findAllProductsByTxoko(String idTxoko) throws BusinessLogicException {
        List<ProductBean> productos = null;
        try{
            LOGGER.info("ProductImplementation: Finding all product from REST service (XML).");
            //Ask webClient for all departments' data.
            productos = ProductWebClient.findAllProductsByTxoko(new GenericType<List<ProductBean>>() {}, idTxoko);
        }catch(ClientErrorException ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception finding all products, {0}",
                    ex.getMessage());
            throw new BusinessLogicException("ProductImplementation: Error updating user:\n"+ex.getMessage());
        }

        return productos;
    }
}
