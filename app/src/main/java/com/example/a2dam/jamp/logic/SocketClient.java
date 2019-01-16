/*
 * 
 * EXCHANGE OBJETS
 * 
 * 
 */
package com.example.a2dam.jamp.logic;


import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.exceptions.PasswordNotOkException;
import com.example.a2dam.jamp.exceptions.UserLoginExistException;
import com.example.a2dam.jamp.exceptions.UserNotExistException;

import messageuserbean.UserBean;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import messageuserbean.Message;

/**
 * Socket client class for communication between socket client and server. It
 * contains two methods for user management.
 *
 * @author Ander
 *
 */
public class SocketClient {

    /**
     * Logger object used to log messages for application.
     */
    private static final Logger LOGGER
            = Logger.getLogger("socketClient");
    /**
     * Port from which the connection to the server socket will be done.
     */
    private final String PORT=String.valueOf(R.string.PORT);
    /**
     * IP address the server socket has to connect to.
     */
    private final String IP=String.valueOf(R.string.IP);

    /**
     * Method for login in a user.
     *
     * @param user The user tipped in
     * @return UserBean Whole information of the user who has logged in
     */
    public UserBean logIn(UserBean user) throws PasswordNotOkException, UserNotExistException,IOException {
        Socket client = null;
        ObjectInputStream input = null;
        ObjectOutputStream output = null;
        UserBean returnBean = null;
        try {
            //Socket creation and Input and output streams creation on the socket
            client = new Socket(IP, Integer.parseInt(PORT));
            output = new ObjectOutputStream(client.getOutputStream());
            input = new ObjectInputStream(client.getInputStream());

            //Write on the socket.
            Message message = new Message(2, user);
            output.writeObject(message);
            //Read from the socket.
            Message received = (Message) input.readObject();
            int mess = received.getCode();
            returnBean = (UserBean) received.getUser();

            switch (mess) {
                //aqui habia un case 2:
                case 21:
                    throw new PasswordNotOkException();
                case 22:
                    throw new UserNotExistException();
                case -2:
                    throw new IOException("Error en lado servidor,");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (client != null) {
                    client.close();
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

        return returnBean;
    }

    /**
     * Method for signing up a user
     *
     * @param user The user tipped in
     */
    public void signUp(UserBean user) throws UserLoginExistException, IOException {

        Socket client = null;
        ObjectInputStream input = null;
        ObjectOutputStream output = null;
        try {
            //Socket creation and Input and output streams creation on the socket
            client = new Socket(IP, Integer.parseInt(PORT));
            output = new ObjectOutputStream(client.getOutputStream());
            input = new ObjectInputStream(client.getInputStream());
            LOGGER.info("En socketclient");
            //Write on the socket.
            Message message = new Message(1, user);
            output.writeObject(message);
            //Read from the socket.
            Message received = (Message) input.readObject();
            int mess = received.getCode();
            //returnBean = (UserBean) received.getUser();

            switch (mess) {
                //aqui habia un case 2:
                case 11:
                    throw new UserLoginExistException();
                case -1:
                    throw new IOException();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (client != null) {
                    client.close();
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
    }

}
