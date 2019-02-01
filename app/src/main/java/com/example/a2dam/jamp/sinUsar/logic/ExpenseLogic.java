/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.a2dam.jamp.sinUsar.logic;


import com.example.a2dam.jamp.sinUsar.exceptions.BusinessLogicException;

/**
 * the expenses interface
 * @author paula
 */
public interface ExpenseLogic {
    /**
     * Method to know the expenses of 1 month of one user
     * @param idUser the id of the user
     * @return returns the expense
     * @throws BusinessLogicException  throws this exceptions if something is wrong.
     */
    Float findMonthExpensesSingleUser(Integer idUser)throws BusinessLogicException;

}