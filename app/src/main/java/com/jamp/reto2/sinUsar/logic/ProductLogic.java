package com.jamp.reto2.sinUsar.logic;


import com.jamp.reto2.dataClasses.ProductBean;
import com.jamp.reto2.sinUsar.exceptions.BusinessLogicException;

import java.util.ArrayList;
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
     * @param idProduct
     * @param idTxoko
     * @return
     * @throws BusinessLogicException
     */
    ProductBean findProductByIdByTxoko(String idProduct, String idTxoko) throws BusinessLogicException;

    /**
     *
     * @return
     * @throws BusinessLogicException
     */
    ArrayList<ProductBean> findAllProducts () throws BusinessLogicException;

    /**
     *
     * @return
     */
    List<ProductBean> findAllProductsByTxoko(String idTxoko) throws BusinessLogicException;

}