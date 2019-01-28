package com.example.a2dam.jamp.threads;



public class ThreadForSocketUser extends Thread {
    /*
    private UserBean user;
    private UserLogic ilogic;
    private int code;

    public UserBean getUser(){
        return user;
    }

    public ThreadForSocketUser(UserBean receivedUser, UserLogic ilogic, int i){
        this.user=receivedUser;
        this.ilogic=ilogic;
        this.code=i;
    }
*/
    /**
     * Method that start the thread
     */
    @Override
    public void run() {/*
        switch (code){
            case 1:
                try {
                    ilogic.userSignUp(user);
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                try {
                    user = ilogic.userLogin(user);
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
                break;
            case 3:
                try {
                    ilogic.userRequestPassword(user.getLogin());
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
                break;
            case 4:
                try {
                    ilogic.UserChangePassword(user);
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
                break;
        }*/
    }
}
