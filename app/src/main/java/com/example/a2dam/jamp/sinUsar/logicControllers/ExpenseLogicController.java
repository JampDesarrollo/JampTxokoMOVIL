/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.a2dam.jamp.sinUsar.logicControllers;



import com.example.a2dam.jamp.sinUsar.exceptions.BusinessLogicException;
import com.example.a2dam.jamp.sinUsar.logic.ExpenseLogic;
import com.example.a2dam.jamp.sinUsar.rest.ExpenseRESTCli;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.ClientErrorException;

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