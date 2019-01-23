package com.example.a2dam.jamp.sockets;

import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.antes_PARA_BORRAR.SocketClient;
import com.example.a2dam.jamp.exceptions.PasswordNotOkException;
import com.example.a2dam.jamp.exceptions.UserNotExistException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import messageuserbean.UserBean;

public class SocketUser {
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

    private Socket userSoc=null;
    private ObjectInputStream input = null;
    private ObjectOutputStream output = null;

    public UserBean userLogIn(UserBean user)throws PasswordNotOkException, UserNotExistException,IOException{
        UserBean returnBean = null;
        try {
            //Socket creation and Input and output streams creation on the socket
            userSoc = new Socket(IP, Integer.parseInt(PORT));
            output = new ObjectOutputStream(userSoc.getOutputStream());
            input = new ObjectInputStream(userSoc.getInputStream());

            //falta el switch con los mensajes

        }catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                if (userSoc != null) {
                    userSoc.close();
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
    public void userSignUp(UserBean user){
        try {
            //Socket creation and Input and output streams creation on the socket
            userSoc = new Socket(IP, Integer.parseInt(PORT));
            output = new ObjectOutputStream(userSoc.getOutputStream());
            input = new ObjectInputStream(userSoc.getInputStream());

            //falta el switch con los mensajes

        }catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(SocketUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (userSoc != null) {
                    userSoc.close();
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
    public void userRequestPassword(String login){
        try {
            //Socket creation and Input and output streams creation on the socket
            userSoc = new Socket(IP, Integer.parseInt(PORT));
            output = new ObjectOutputStream(userSoc.getOutputStream());
            input = new ObjectInputStream(userSoc.getInputStream());

            //falta el switch con los mensajes

        }catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                if (userSoc != null) {
                    userSoc.close();
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
    public void UserChangePassword(UserBean user){
        try {
            //Socket creation and Input and output streams creation on the socket
            userSoc = new Socket(IP, Integer.parseInt(PORT));
            output = new ObjectOutputStream(userSoc.getOutputStream());
            input = new ObjectInputStream(userSoc.getInputStream());

            //falta el switch con los mensajes

        }catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                if (userSoc != null) {
                    userSoc.close();
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
