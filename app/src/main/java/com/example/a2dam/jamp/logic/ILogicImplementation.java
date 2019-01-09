/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.a2dam.jamp.logic;


import com.example.a2dam.jamp.exceptions.PasswordNotOkException;
import com.example.a2dam.jamp.exceptions.UserLoginExistException;
import com.example.a2dam.jamp.exceptions.UserNotExistException;

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
public class ILogicImplementation implements ILogic {

    private final SocketClient socket = new SocketClient();
    private UserBean returnUser;
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
        LOGGER.info("userSignUp in ILogicImplementation");

        socket.signUp(user);
    }

    /**
     * This method returns a UserBean after and make Login.
     *
     * @param user The UserBean object to be added
     * @return UserBean
     * @throws UserNotExistException, PasswordNotOkException, Exception
     */
    @Override
    public UserBean userLogin(UserBean user)
            throws UserNotExistException, PasswordNotOkException, Exception {
        returnUser = socket.logIn(user);

        return returnUser;
    }
//
//    @Override
//    public List<Expense> findExpensesMonth(Integer idTxoko) throws Exception {
//        return null;
//    }

}
