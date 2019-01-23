package com.example.a2dam.jamp.threads;

import com.example.a2dam.jamp.logics.EventLogic;

import messageuserbean.UserBean;

public class ThreadForSocketEvent extends Thread {
    private EventBean event;
    private UserBean user;
    private EventLogic ilogic;
    private int code;

    public EventBean getEvent(){
        return event;
    }
    public UserBean getUser(){
        return user;
    }

    public ThreadForSocketEvent(UserBean receivedUser,EventBean receivedEvent, EventLogic ilogic, int i){
        this.user=receivedUser;
        this.event=receivedEvent;
        this.ilogic=ilogic;
        this.code=i;
    }

    /**
     * Method that start the thread
     */
    @Override
    public void run() {
        switch (code) {
            case 1:
                try {
                    ilogic.findAllEvents(event);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                try {
                    ilogic.attendEvent(user,event);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}
