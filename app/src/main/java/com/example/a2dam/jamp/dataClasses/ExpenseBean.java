/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.a2dam.jamp.dataClasses;

import java.io.Serializable;


/**
 * @author WIN10
 */
public class ExpenseBean implements Serializable {

    private Integer idExpense;
    private String date;
    private UserBean user;
    private String type;
    private String description;
    private Float price;

    public ExpenseBean() {
    }

    public ExpenseBean(String date, UserBean user, String type,
                       String description,
                       Float price) {
        this.date = date;
        this.user = user;
        this.type = type;
        this.description = description;
        this.price = price;
    }

    /**
     * @return the idExpense
     */
    public Integer getIdExpense() {
        return this.idExpense;
    }

    /**
     * @param idExpense the idExpense to set
     */
    public void setIdExpense(Integer idExpense) {
        this.idExpense = idExpense;
    }

    /**
     * @return the user
     */
    public UserBean getUser() {
        return this.user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserBean user) {
        this.user = user;
    }

    /**
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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
        this.description = description;
    }

    /**
     * @return the price
     */
    public Float getPrice() {
        return this.price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Float price) {
        this.price = price;
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
        this.date = date;
    }

}
