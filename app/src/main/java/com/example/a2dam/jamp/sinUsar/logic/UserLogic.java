/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.a2dam.jamp.sinUsar.logic;


import com.example.a2dam.jamp.dataClasses.UserBean;
import com.example.a2dam.jamp.sinUsar.exceptions.BusinessLogicException;

/**
 * Business logic interface encapsulating business methods for users management.
 *
 * @author Ander
 */
public interface UserLogic {

    /**
     * This method adds a new created UserBean.
     *
     * @param user The UserBean object to be added.
     * @throws BusinessLogicException If there is any error while processing.
     */
    void createUser(UserBean user) throws BusinessLogicException;


    /**
     * This method returns the UserBean of the user who is trying to log-in in
     * Mobile app.
     *
     * @param login Login of the user to find.
     * @param passw Password of the user to find.
     * @return Userbean that wants to log-in.
     * @throws BusinessLogicException If there is any error while processing.
     */
    UserBean findUserByLoginPasswMov(String login, String passw)
            throws BusinessLogicException;

    /**
     * This method returns a boolean that confirms a user's password has beens
     * changed for Mobile app.
     *
     * @param idUser Id of the user.
     * @param oldpass Old password of the user.
     * @param newPassw New password of the user.
     * @return Boolean if all ok.
     * @throws BusinessLogicException If there is any error while processing.
     */
    Boolean findUserChangePasswMov(Integer idUser, String oldpass, String newPassw)
            throws BusinessLogicException;

    /**
     * This method returns a boolean that confirms an email has been sent to the
     * user who wanted his password reset.
     *
     * @param login Login of the user to find.
     * @return boolean if all ok.
     * @throws BusinessLogicException If there is any error while processing.
     */
    Boolean findUserForgotPassw(String login) throws BusinessLogicException;


}
