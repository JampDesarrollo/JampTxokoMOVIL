package com.example.a2dam.jamp.antes_PARA_BORRAR;

/**
 * Method for comunication for server
 * @autor Ander
 */

import com.example.a2dam.jamp.logics.UserLogic;

import messageuserbean.UserBean;

/**
 * Creation fro the thread
 */
public class ThreadForSocketClient extends Thread {

    private UserBean user;
    private UserLogic ilogic;
    private int code;

    public UserBean getUser(){
     return user;
    }

    public ThreadForSocketClient(UserBean receivedUser, UserLogic ilogic, int i){
        this.user=receivedUser;
        this.ilogic=ilogic;
        this.code=i;
    }

    /**
     * Method that start the thread
     */
    @Override
    public void run() {
        if(code==1){
            try {
                ilogic.userSignUp(user);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }else{
            try {
                user = ilogic.userLogin(user);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }

    }

}
