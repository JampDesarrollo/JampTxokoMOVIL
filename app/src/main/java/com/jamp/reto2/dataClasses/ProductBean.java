 /*
  * To change this license header, choose License Headers in Project Properties.
  * To change this template file, choose Tools | Templates
  * and open the template in the editor.
  */
 package com.jamp.reto2.dataClasses;


 import java.io.Serializable;
 import java.util.List;


 /**
  *
  * @author Julen
  */
 public class ProductBean implements Serializable{

   /**
    *
    */
   private Integer idProduct;

   /**
    *
    */
   private String stock;

   /**
    *
    */
   private String name;

   /**
    *
    */
   private String price;

   /**
    *
    */
   private String description;

   /**
    *
    */
   private List<TxokoBean> txokos;

   public ProductBean(){
   }

   public ProductBean(String name,
                      String description,
                      String stock,
                      String price,
                      String venta){
     this.name=name;
     this.description=description;
     this.stock=stock;
     this.price = price;
   }

   /**
    * @return the idProduct
    */
   public Integer getIdProduct() {
     return this.idProduct;
   }
   /**
    * @param idProduct the idProduct to set
    */
   public void setIdProduct(Integer idProduct) {
     this.idProduct=idProduct;
   }

   /**
    * @return the stock
    */
   public String getStock() {
     return this.stock;
   }

   /**
    * @param stock the stock to set
    */
   public void setStock(String stock) {
     this.stock=stock;
   }

   /**
    * @return the name
    */
   public String getName() {
     return this.name;
   }

   /**
    * @param name the name to set
    */
   public void setName(String name) {
     this.name=name;
   }

   /**
    * @return the price
    */
   public String getPrice() {
     return this.price;
   }

   /**
    * @param price the price to set
    */
   public void setPrice(String price) {
     this.price=price;
   }

   /**
    * @return the description
    */
   public String getDescription() {
     return this.description;
   }

   /**
    * @param description the description to set
    */
   public void setDescription(String description) {
     this.description=description;
   }

   /**
    * @return the txokos
    */
   public List<TxokoBean> getTxokos() {
     return txokos;
   }

   /**
    * @param txokos the txokos to set
    */
   public void setTxokos(List<TxokoBean> txokos) {
     this.txokos = txokos;
   }

   @Override
   public boolean equals(Object object) {
     // TODO: Warning - this method won't work in the case the id fields are not set
     if (!(object instanceof ProductBean)) {
       return false;
     }
     ProductBean other = (ProductBean) object;
     if ((this.getIdProduct() == null && other.getIdProduct() != null) || (this.getIdProduct() != null && !this.getIdProduct().equals(other.getIdProduct()))) {
       return false;
     }
     if ((this.getName() == null && other.getName() != null) || (this.getName() != null && !this.getName().equals(other.getName()))) {
       return false;
     }
     if ((this.getDescription()== null && other.getDescription()!= null) || (this.getDescription() != null && !this.getDescription().equals(other.getDescription()))) {
       return false;
     }
     if ((this.getStock() == null && other.getStock() != null) || (this.getStock() != null && !this.getStock().equals(other.getStock()))) {
       return false;
     }
       return (this.getPrice() != null || other.getPrice() == null) && (this.getPrice() == null || this.getPrice().equals(other.getPrice()));
   }
 }
