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
package com.example.a2dam.jamp.dataClasses;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@XmlRootElement(name = "event")
public class EventBean implements Serializable {

  private Integer idEvent;
  private SimpleStringProperty description;
  private SimpleStringProperty price;
  private SimpleStringProperty name;
  private SimpleStringProperty img;
  private SimpleStringProperty date;
  private List<TxokoBean> txokos;
  private List<UserBean> users;

  public EventBean() {
    this.idEvent = idEvent;
    this.description = new SimpleStringProperty();
    this.price = new SimpleStringProperty();
    this.name = new SimpleStringProperty();
    this.img = new SimpleStringProperty();
    this.date = new SimpleStringProperty();
    this.txokos = txokos;
    this.users = users;
  }

  public EventBean(String name,
                   String description,
                   String date,
                   String img,
                   String price) {
    this.name = new SimpleStringProperty(name);
    this.description = new SimpleStringProperty(description);
    this.date = new SimpleStringProperty(date);
    this.img = new SimpleStringProperty(img);
    this.price = new SimpleStringProperty(price);
  }
  /**
   * @return the description
   */
  public String getDescription() {
    return this.description.get();
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description.set(description);
  }

  /**
   * @return the price
   */
  public String getPrice() {
    return this.price.get();
  }

  /**
   * @param price the price to set
   */
  public void setPrice(String price) {
    this.price.set(price);
  }

  /**
   * @return the name
   */
  public String getName() {
    return this.name.get();
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name.set(name);
  }

  /**
   * @return the img
   */
  public String getImg() {
    return this.img.get();
  }

  /**
   * @param img the img to set
   */
  public void setImg(String img) {
    this.img.set(img);
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
    return this.date.get();
  }

  /**
   * @param date the date to set
   */
  public void setDate(String date) {
    this.date.set(date);
  }
}
