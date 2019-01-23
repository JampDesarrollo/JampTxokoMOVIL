/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.a2dam.jamp.logicControllers;

import com.example.a2dam.jamp.logics.TelephoneLogic;
import com.example.a2dam.jamp.sockets.SocketTelephone;
import java.util.logging.Logger;


/**
 * This class implements iLogic interface
 *
 * @author Julen
 * @author Ander
 * @author Markel
 * @author Paula
 */
public class TelephoneLogicController implements TelephoneLogic {

    private final SocketTelephone socket = new SocketTelephone();
    private static final Logger TELEPHONELOGGER = Logger.getLogger("socketTelephone");

    public ArrayList<TelephoneBean> findAllTelephone(TelephoneBean telephone) throws algo {
        TELEPHONELOGGER.info("findAllTelephones in TelephoneLogicController");
        return socket.findAllTelephone(telephone);
    }
}
