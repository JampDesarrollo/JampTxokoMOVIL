/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.a2dam.jamp.logics;

import com.example.a2dam.jamp.exceptions.PasswordNotOkException;
import com.example.a2dam.jamp.exceptions.UserLoginExistException;
import com.example.a2dam.jamp.exceptions.UserNotExistException;

import java.util.ArrayList;

import messageuserbean.UserBean;

/**
 * Es la interfaz de lógica. Si hay que hacer una llamada entre dos objetos de
 * diferentes clases, se necesita una interfaz.
 * @author Julen
 * @author Ander
 * @author Markel
 * @author Paula
 */
public interface EventLogic {
    ArrayList<EventBean> findAllEvents (EventBean event) throws algo;

    void attendEvent(UserBean user,EventBean event) throws algo;
}
