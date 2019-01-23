/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.a2dam.jamp.logicControllers;


import com.example.a2dam.jamp.exceptions.PasswordNotOkException;
import com.example.a2dam.jamp.exceptions.UserLoginExistException;
import com.example.a2dam.jamp.exceptions.UserNotExistException;
import com.example.a2dam.jamp.logics.EventLogic;
import com.example.a2dam.jamp.logics.ProductLogic;
import com.example.a2dam.jamp.logics.UserLogic;
import com.example.a2dam.jamp.sockets.SocketEvent;
import com.example.a2dam.jamp.sockets.SocketTelephone;
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
public class EventLogicController implements EventLogic {

    private final SocketEvent socket = new SocketEvent();
    private static final Logger EVENTLOGGER = Logger.getLogger("socketEvent");

    public ArrayList<EventBean> findAllEvents (EventBean event) throws algo{
        EVENTLOGGER.info("findAllEvents in EventLogicController");
        return socket.findAllEvents();
    }

    @Override
    public void attendEvent(UserBean user, EventBean event) throws algo {
        EVENTLOGGER.info("attendEvent in EventLogicController");
        socket.attendEvent(user,event);
    }
}