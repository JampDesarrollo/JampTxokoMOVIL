package com.example.a2dam.jamp.threads;

import com.example.a2dam.jamp.logics.ExpenseLogic;
import com.example.a2dam.jamp.logics.UserLogic;

import messageuserbean.UserBean;

public class ThreadForSocketExpense extends Thread {
    private ExpenseBean expense;
    private ExpenseLogic ilogic;
    private int code;

    public ExpenseBean getExpense(){
        return expense;
    }

    public ThreadForSocketExpense(ExpenseBean receivedExpense, ExpenseLogic ilogic, int i){
        this.expense=receivedExpense;
        this.ilogic=ilogic;
        this.code=i;
    }

    /**
     * Method that start the thread
     */
    @Override
    public void run() {
        switch (code){
            case 1:
                try {
                    ilogic.findMonthExpenses(expense);
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}
