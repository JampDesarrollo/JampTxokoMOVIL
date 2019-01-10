package com.example.a2dam.jamp.fragments;

import java.io.Serializable;

/**
 *
 * @author Julen
 */
public class Telephone implements Serializable{
    private Integer id;
    private String nombre;
    private Integer telephon;
    private String description;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the telephon
     */
    public Integer getTelephon() {
        return telephon;
    }

    /**
     * @param telephon the telephon to set
     */
    public void setTelephon(Integer telephon) {
        this.telephon = telephon;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

}