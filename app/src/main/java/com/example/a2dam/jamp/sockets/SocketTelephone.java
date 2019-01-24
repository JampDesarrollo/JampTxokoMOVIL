package com.example.a2dam.jamp.sockets;

import android.widget.TextView;

import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.antes_PARA_BORRAR.SocketClient;
import com.example.a2dam.jamp.dataClasses.Telephone;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.json.JSONArray;

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
    ArrayList<TelephoneBean> returnTelephone=null;
    public ArrayList<TelephoneBean> findAllTelephone(TelephoneBean telephone) throws algo {

        try {
            //Socket creation and Input and output streams creation on the socket
            telephoneSoc = new Socket(IP, Integer.parseInt(PORT));
            output = new ObjectOutputStream(telephoneSoc.getOutputStream());
            input = new ObjectInputStream(telephoneSoc.getInputStream());

            //llamar al mongodb connect
            mongoConnect();
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

    public void mongoConnect(){
        MongoClient mongoclient = MongoClients.create("mongodb://"+ R.string.MongoDBIP+":"+R.string.MongoDBPort+"/"+R.string.MongoDBName);
        MongoDatabase mongoDB = mongoclient.getDatabase(String.valueOf(R.string.MongoDBName));
        MongoCollection<Document> collection = mongoDB.getCollection(String.valueOf(R.string.MongoDBCollectionName));

        FindIterable<Document> fi = collection.find();
        MongoCursor<Document> cursor = fi.iterator();
        ArrayList<Telephone> telephones=new ArrayList<>();
        try {
            if (!cursor.hasNext()) {
                System.out.println("No se ha encontrado ningún documento");
            }
            int i = 0;
            while (cursor.hasNext()) {
                //añadir la coleccion a un array
                //telephones.add(cursor,fi);
                System.out.println(++i + cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
    }
}
