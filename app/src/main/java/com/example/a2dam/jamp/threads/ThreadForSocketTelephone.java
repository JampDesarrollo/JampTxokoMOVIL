package com.example.a2dam.jamp.threads;

import android.util.Log;
import android.view.View;

import com.example.a2dam.jamp.dataClasses.Telephone;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.ArrayList;

public class ThreadForSocketTelephone extends Thread {/*
    private ArrayList<Telephone> telephones;
    private int code;

    public ThreadForSocketTelephone(ArrayList<Telephone> receivedTelephones, int i){
        this.telephones=receivedTelephones;
        this.code=i;
    }*/

    /**
     * Method that start the thread
     */
    @Override
    public void run() {
       /* switch (code){
            case 1:
                try {

                }catch (Exception e){
                    throw new RuntimeException(e);
                }
                break;
        }*/
    }
}
