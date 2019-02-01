/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.a2dam.jamp.dataClasses;



/**
 * @author Usuario
 */
public class TxokoBean {

    private UserBean users;
    private Integer idTxoko;
    private String direction;
    private Float monthFee;
    private String name;
    private String town;

    public TxokoBean(Integer idTxoko, String direction, Float monthFee, String name, String town) {
        this.idTxoko = idTxoko;
        this.direction = direction;
        this.monthFee = monthFee;
        this.name = name;
        this.town = town;
    }

    public TxokoBean() {
    }

    /**
     * @return the idTxoko
     */
    public Integer getIdTxoko() {
        return this.idTxoko;
    }

    /**
     * @param idTxoko the idTxoko to set
     */
    public void setIdTxoko(Integer idTxoko) {
        this.idTxoko = idTxoko;
    }

    /**
     * @return the direction
     */
    public String getDirection() {
        return this.direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * @return the monthFee
     */
    public Float getMonthFee() {
        return this.monthFee;
    }

    /**
     * @param monthFee the monthFee to set
     */
    public void setMonthFee(Float monthFee) {
        this.monthFee = monthFee;
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
        this.name = name;
    }

    /**
     * @return the town
     */
    public String getTown() {
        return this.town;
    }

    /**
     * @param town the town to set
     */
    public void setTown(String town) {
        this.town = town;
    }

}
