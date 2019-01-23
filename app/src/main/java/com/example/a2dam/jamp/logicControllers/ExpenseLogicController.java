/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.a2dam.jamp.logicControllers;


import com.example.a2dam.jamp.exceptions.PasswordNotOkException;
import com.example.a2dam.jamp.exceptions.UserLoginExistException;
import com.example.a2dam.jamp.exceptions.UserNotExistException;
import com.example.a2dam.jamp.logics.ExpenseLogic;
import com.example.a2dam.jamp.logics.UserLogic;
import com.example.a2dam.jamp.sockets.SocketExpense;
import com.example.a2dam.jamp.sockets.SocketUser;

import java.util.ArrayList;
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
public class ExpenseLogicController implements ExpenseLogic {

    private final SocketExpense socket = new SocketExpense();
    private static final Logger EXPENSELOGGER = Logger.getLogger("socketExpense");

    public ArrayList<ExpenseBean> findMonthExpenses(UserBean user) throws algo{
        EXPENSELOGGER.info("findMonthExpenses in ExpenseLogicController");
        return socket.findMonthExpenses(user);
    }
}
