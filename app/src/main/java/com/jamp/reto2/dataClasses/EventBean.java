/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Usuario
 */
public class EventBean implements Serializable {

  private Integer idEvent;
  private String description;
  private String price;
  private String name;
  private String img;
  private String date;
  private List<TxokoBean> txokos;
  private List<UserBean> users;

  public EventBean() {
  }

  public EventBean(String name,
                   String description,
                   String date,
                   String img,
                   String price) {
    this.name = name;
    this.description = description;
    this.date = date;
    this.img = img;
    this.price = price;
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
   * @return the img
   */
  public String getImg() {
    return this.img;
  }

  /**
   * @param img the img to set
   */
  public void setImg(String img) {
    this.img=img;
  }

  /**
   * @return the users
   */
  public List<UserBean> getUsers() {
    return users;
  }

  /**
   * @param users the users to set
   */
  public void setUsers(List<UserBean> users) {
    this.users = users;
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

    /*
    public String getDate(){

    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
    String result = dateformat.format(date);
    return result;
    }*/
  /**
   * @return the idEvent
   */
  public Integer getIdEvent() {
    return idEvent;
  }

  /**
   * @param idEvent the idEvent to set
   */
  public void setIdEvent(Integer idEvent) {
    this.idEvent = idEvent;
  }

  /**
   * @return the date
   */
  public String getDate() {
    return this.date;
  }

  /**
   * @param date the date to set
   */
  public void setDate(String date) {
    this.date=date;
  }
}
