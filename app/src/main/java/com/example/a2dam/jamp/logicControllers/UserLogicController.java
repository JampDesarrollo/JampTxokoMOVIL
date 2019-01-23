/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.a2dam.jamp.logicControllers;


import com.example.a2dam.jamp.exceptions.PasswordNotOkException;
import com.example.a2dam.jamp.exceptions.UserLoginExistException;
import com.example.a2dam.jamp.exceptions.UserNotExistException;
import com.example.a2dam.jamp.logics.UserLogic;
import com.example.a2dam.jamp.sockets.SocketUser;

import java.util.logging.Logger;

import messageuserbean.UserBean;

/**
 * This class implements iLogic interface
 *
 * @author Julen
 * @author Ander
 * @author Markel
 * @author Paula
 */
public class UserLogicController implements UserLogic {

    private final SocketUser socket = new SocketUser();
    private static final Logger LOGGER
            = Logger.getLogger("socketClient");

    /**
     * This method register a new UserBean in database
     *
     * @param user The UserBean object to be added
     * @throws UserLoginExistException, Exception.
     */
    @Override
    public void userSignUp(UserBean user) throws UserLoginExistException, Exception {
        LOGGER.info("userSignUp in UserLogicController");
        socket.userSignUp(user);
    }

    /**
     * This method returns a UserBean after and make Login.
     *
     * @param user The UserBean object to be added
     * @return UserBean
     * @throws UserNotExistException, PasswordNotOkException, Exception
     */
    @Override
    public UserBean userLogin(UserBean user)throws UserNotExistException, PasswordNotOkException, Exception {
        LOGGER.info("userLogin in UserLogicController");
        return socket.userLogIn(user);
    }

    @Override
    public void userRequestPassword(String login) {
        LOGGER.info("userRequestPassword in UserLogicController");
        socket.userRequestPassword(login);
    }

    @Override
    public void UserChangePassword(UserBean user) {
        LOGGER.info("UserChangePassword in UserLogicController");
        socket.UserChangePassword(user);
    }
}
