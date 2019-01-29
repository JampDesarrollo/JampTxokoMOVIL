/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.a2dam.jamp.logicControllers;

import com.example.a2dam.jamp.dataClasses.UserBean;
import com.example.a2dam.jamp.exceptions.BusinessLogicException;
import com.example.a2dam.jamp.logic.UserLogic;
import com.example.a2dam.jamp.rest.UserRESTClient;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.GenericType;

/**
 * This class implements UserLogic business logic interface using a RESTful web
 * client to access bussines logic in an Java EE application server.
 *
 * @author Ander
 */
public class UserLogicController implements UserLogic {

    /**
     * REST users web client
     */
    private UserRESTClient webClient;
    /**
     * Logger for the class.
     */
    private static final Logger LOGGER
            = Logger.getLogger("jampclientside.logic");

    /**
     * Create a UserRESTClient object. It constructs a web client for accessing
     * a RESTful service that provides business logic in an application server.
     */
    public UserLogicController() {
        webClient = new UserRESTClient();
    }

    /**
     * This method returns a boolean that confirms an email has been sent to the
     * user who wanted his password reset.
     *
     * @param login Login of the user to find.
     * @return boolean if all ok.
     * @throws BusinessLogicException If there is any error while processing.
     */
    @Override
    public Boolean findUserForgotPassw(String login) throws BusinessLogicException {
        Boolean allOk = false;
        try {
            LOGGER.info("UserLogicController: Reading all users.");
            allOk = webClient.findUserForgotPassword(Boolean.class, login);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserLogicController: finding a user by login:",
                    e.getMessage());
            throw new BusinessLogicException("Error finding user: " + e.getMessage());
        }
        return allOk;
    }

    /**
     * This method adds a new created UserBean.
     *
     * @param user The UserBean object to be added.
     * @throws BusinessLogicException If there is any error while processing.
     */
    @Override
    public void createUser(UserBean user) throws BusinessLogicException {
        try {
            LOGGER.log(Level.INFO, "UserLogicController: Creating user {0}.", user.getLogin());
            webClient.createUser(user);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserLogicController: Exception updating user.{0}",
                    e.getMessage());
            throw new BusinessLogicException("Error creating user: " + e.getMessage());
        }
    }

    /**
     * This method returns the UserBean of the user who is trying to log-in in
     * Mobile app.
     *
     * @param login Login of the user to find.
     * @param passw Password of the user to find.
     * @return Userbean that wants to log-in.
     * @throws BusinessLogicException If there is any error while processing.
     */
    @Override
    public UserBean findUserByLoginPasswMov(String login, String passw) throws BusinessLogicException {
        UserBean user = null;
        try {
            LOGGER.info("UserLogicController: finding a user by login and passw mov.");
            user = webClient.findUserByLoginPasswMov(UserBean.class, login, passw);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserLogicController: finding a user by login and passw mov:",
                    e.getMessage());
            throw new BusinessLogicException("Error finding user: " + e.getMessage());
        }
        return user;
    }

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
    @Override
    public Boolean findUserChangePasswMov(Integer idUser, String oldpass, String newPassw) throws BusinessLogicException {
        Boolean allOk = false;
        try {
            LOGGER.info("UserLogicController: finding user to change passw.");
            allOk = webClient.findUserChangePasswMov(Boolean.class, idUser.toString(), oldpass, newPassw);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserLogicController:finding user to change passw:",
                    e.getMessage());
            throw new BusinessLogicException("Error finding user: " + e.getMessage());
        }
        return allOk;
    }

}
