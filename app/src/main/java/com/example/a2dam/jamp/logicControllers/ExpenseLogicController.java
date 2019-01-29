/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.a2dam.jamp.logic;

import com.example.a2dam.jamp.dataClasses.ExpenseBean;
import com.example.a2dam.jamp.dataClasses.UserBean;
import com.example.a2dam.jamp.exceptions.BusinessLogicException;
import com.example.a2dam.jamp.rest.ExpenseRESTCli;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author WIN10
 */
public class ExpenseLogicController implements ExpenseLogic {

    private ExpenseRESTCli webClient;
    private static final Logger LOGGER = Logger.getLogger("javafxapplicationud3example");

    public ExpenseLogicController() {
        webClient = new ExpenseRESTCli();
    }
    /**
     * Method to find all expenses of my txoko
     * @param idTxoko the id of the txoko
     * @return it returns a collection of the expenses of the txoko
     * @throws BusinessLogicException  throws this exceptions if something is wrong.
     */
    @Override
    public Collection<ExpenseBean> findAllExpensesUsers(String idTxoko) throws BusinessLogicException {
        List<ExpenseBean> expense = null;
        try {
            LOGGER.info("EventsManager: Finding all expense of my txoko from REST service (XML).");
            expense = webClient.findAllExpensesUsers(new GenericType<List<ExpenseBean>>() {
            }, idTxoko);
        } catch (ClientErrorException e) {
            LOGGER.log(Level.SEVERE,
                    "EventsManager: Exception finding all users, {0}",
                    e.getMessage());
            throw new BusinessLogicException("Error finding all users:\n" + e.getMessage());
        }

        return expense;
    }
     /**
     * Method to return all the users
     * @return it returns the users
     * @throws BusinessLogicException  throws this exceptions if something is wrong.
     */

    @Override
    public Collection<UserBean> getAllUsers() throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      /**
     * Method to konow the expenses of 1 month
     * @param idTxoko the id of the txoko
     * @return returns the expenses of 1 month
     * @throws BusinessLogicException  throws this exceptions if something is wrong.
     */
    @Override
    public Collection<ExpenseBean> findMonthExpensesUsers(String idTxoko) throws BusinessLogicException {
        List<ExpenseBean> expense = null;
        try {
            LOGGER.info("EventsManager: Finding all expense of my txoko of a month from REST service (XML).");
            expense = webClient.findMonthExpensesUsers(new GenericType<List<ExpenseBean>>() {
            }, idTxoko);
        } catch (ClientErrorException e) {
            LOGGER.log(Level.SEVERE,
                    "EventsManager: Exception finding all users, {0}",
                    e.getMessage());
            throw new BusinessLogicException("Error finding all users:\n" + e.getMessage());
        }

        return expense;
    }
     /**
     * Create an expense
     * @param expense the expense we want to create
     * @throws BusinessLogicException  throws this exceptions if something is wrong.
     */
    @Override
    public void CreateExpense(ExpenseBean expense) throws BusinessLogicException {
        try {
            LOGGER.log(Level.INFO, "Creating expense {0}.", expense.getIdExpense());
            //Send user data to web client for creation. 
            webClient.createExpense(expense);
        } catch (ClientErrorException e) {
            LOGGER.log(Level.SEVERE,
                    "EventsManager: Exception finding all users, {0}",
                    e.getMessage());
            throw new BusinessLogicException("Error finding all users:\n" + e.getMessage());
        }
    }
    /**
     * Method to know the expenses of 1 month of one user
     * @param idUser the id of the user
     * @return returns the expense
     * @throws BusinessLogicException  throws this exceptions if something is wrong.
     */
    @Override
    public Float findMonthExpensesSingleUser(Integer idUser) throws BusinessLogicException {
        Float expenses = 0.0f;
       try {
            LOGGER.info("EventsManager: Finding all events of my txoko from REST service (XML).");
            expenses = webClient.findMonthExpensesSingleUser(Float.class, idUser);
        } catch (ClientErrorException e) {
            LOGGER.log(Level.SEVERE,
                    "EventsManager: Exception finding all users, {0}",
                    e.getMessage());
            throw new BusinessLogicException("Error finding all users:\n" + e.getMessage());
        }

        return expenses;
    }

}