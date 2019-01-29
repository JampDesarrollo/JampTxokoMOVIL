/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.a2dam.jamp.dataClasses;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Data Transfer Object used in UI and client side for representing User entity.
 * It is also used as data model for a TableView in the UI.
 *
 * @author Ander
 */
@XmlRootElement(name = "user")
public class UserBean implements Serializable {

    private ExpenseBean expenses;
    /**
     *
     */

    private Integer idUser;
    /**
     *
     */

    private TxokoBean txoko;
    /**
     *
     */

    private String login;
    /**
     *
     */
    private String email;
    /**
     *
     */
    private String fullname;
    /**
     *
     */
    private String password;
    /**
     *
     */

    private UserStatus status;
    /**
     *
     */

    private UserPrivilege privilege;
    /**
     *
     */

    private String lastAccess;

    /**
     *
     */
    private String lastPasswordChange;

    /**
     * Empty contructor
     */
    public UserBean() {
    }

    /**
     * Full constructor
     *
     * @param idUser    id user
     * @param login     login
     * @param email     email
     * @param fullname  fullname
     * @param status    status
     * @param privilege privilege
     */
    public UserBean(Integer idUser, String login, String email,
                    String fullname, UserStatus status, UserPrivilege privilege) {
        this.idUser = idUser;
        this.login = login;
        this.email = email;
        this.fullname = fullname;
        this.status = status;
        this.privilege = privilege;
    }

    /**
     * @return the idUser
     */
    public Integer getIdUser() {
        return this.idUser;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    /**
     * @return the idTxoko
     */
    public TxokoBean getTxoko() {
        return this.txoko;
    }

    /**
     * @param txoko the txoko to set
     */
    public void setTxoko(TxokoBean txoko) {
        this.txoko = txoko;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return this.login;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the fullname
     */
    public String getFullname() {
        return this.fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the status
     */
    public UserStatus getStatus() {
        return this.status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    /**
     * @return the privilege
     */
    public UserPrivilege getPrivilege() {
        return this.privilege;
    }

    /**
     * @param privilege the privilege to set
     */
    public void setPrivilege(UserPrivilege privilege) {
        this.privilege = privilege;
    }

    /**
     * @return the lastAccess
     */
    public String getLastAccess() {
        return this.lastAccess;
    }

    /**
     * @param lastAccess the lastAccess to set
     */
    public void setLastAccess(String lastAccess) {
        this.lastAccess = lastAccess;
    }

    /**
     * @return the expenses
     */
    public ExpenseBean getExpenses() {
        return this.expenses;
    }

    /**
     * @param expenses the expenses to set
     */
    public void setExpenses(ExpenseBean expenses) {
        this.expenses = expenses;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the lastPasswordChange
     */
    public String getLastPasswordChange() {
        return this.lastPasswordChange;
    }

    /**
     * @param lastPasswordChange the lastPasswordChange to set
     */
    public void setLastPasswordChange(String lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }
}
