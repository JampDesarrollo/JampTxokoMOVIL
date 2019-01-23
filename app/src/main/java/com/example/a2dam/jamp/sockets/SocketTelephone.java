package com.example.a2dam.jamp.sockets;

import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.antes_PARA_BORRAR.SocketClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketTelephone {
    /**
     * Logger object used to log messages for application.
     */
    private static final Logger LOGGER
            = Logger.getLogger("socketUser");
    /**
     * Port from which the connection to the server socket will be done.
     */
    private final String PORT=String.valueOf(R.string.PORT);
    /**
     * IP address the server socket has to connect to.
     */
    private final String IP=String.valueOf(R.string.IP);

    private Socket telephoneSoc=null;
    private ObjectInputStream input = null;
    private ObjectOutputStream output = null;
    public ArrayList<TelephoneBean> findAllTelephone(TelephoneBean telephone) throws algo {
        ArrayList<TelephoneBean> returnTelephone=null;
        try {
            //Socket creation and Input and output streams creation on the socket
            telephoneSoc = new Socket(IP, Integer.parseInt(PORT));
            output = new ObjectOutputStream(telephoneSoc.getOutputStream());
            input = new ObjectInputStream(telephoneSoc.getInputStream());

            //falta el switch con los mensajes

        }catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                if (telephoneSoc != null) {
                    telephoneSoc.close();
                }
                if (input != null) {
                    input.close();
                }
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return returnTelephone;
    }
}
