package com.example.a2dam.jamp.threads;

import com.example.a2dam.jamp.logics.ProductLogic;

public class ThreadForSocketProduct extends Thread {
    private ProductBean product;
    private ProductLogic ilogic;
    private int code;

    public ProductBean getProduct(){
        return product;
    }

    public ThreadForSocketProduct(ProductBean receivedProduct, ProductLogic ilogic, int i){
        this.product=receivedProduct;
        this.ilogic=ilogic;
        this.code=i;
    }

    /**
     * Method that start the thread
     */
    @Override
    public void run() {
        switch (code){
            case 1:
                try {
                    ilogic.findAllProducts(product);
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                try {
                    ilogic.consumeProduct(product);
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}
