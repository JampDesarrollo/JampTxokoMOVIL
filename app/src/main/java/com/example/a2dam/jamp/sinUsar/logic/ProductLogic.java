package com.example.a2dam.jamp.logic;

import com.example.a2dam.jamp.dataClasses.ProductBean;
import com.example.a2dam.jamp.exceptions.BusinessLogicException;
import com.example.a2dam.jamp.exceptions.ProductExist;

import java.util.List;

/**
 * Es la interfaz de lógica de los productos. Si hay que hacer una llamada entre dos objetos de
 * diferentes clases, se necesita una interfaz.
 *
 * @author Julen
 */
public interface ProductLogic {


    /**
     *
     * @param product it returns the product
     * @throws BusinessLogicException it throws if there is anything wrong
     */
    public void deleteProduct(ProductBean product) throws BusinessLogicException;

    /**
     *
     * @param product it returns the product
     * @throws BusinessLogicException it throws if there is anything wrong
     */
    public void updateProduct(ProductBean product) throws BusinessLogicException;

    /**
     *
     * @param product it returns the product
     * @throws BusinessLogicException it throws if there is anything wrong
     */
    public void createProduct(ProductBean product) throws BusinessLogicException;

    /**
     *
     * @param idProduct
     * @return
     * @throws BusinessLogicException
     */
    public ProductBean findProductById(String idProduct) throws BusinessLogicException;

    /**
     *
     * @param idProduct
     * @param idTxoko
     * @return
     * @throws BusinessLogicException
     */
    public ProductBean findProductByIdByTxoko(String idProduct, String idTxoko) throws BusinessLogicException;

    /**
     *
     * @param name
     * @param idTxoko
     * @return
     * @throws BusinessLogicException
     */
    public List<ProductBean> findProductByName(String name, String idTxoko) throws BusinessLogicException;

    /**
     *
     * @return
     * @throws BusinessLogicException
     */
    public List<ProductBean> findAllProducts () throws BusinessLogicException;

    /**
     *
     * @return
     */
    public List<ProductBean> findAllProductsByTxoko(String idTxoko) throws BusinessLogicException;

    /**
     *
     * @param id
     * @throws ProductExist
     */
    public void isProductExist(Integer id) throws ProductExist;



}