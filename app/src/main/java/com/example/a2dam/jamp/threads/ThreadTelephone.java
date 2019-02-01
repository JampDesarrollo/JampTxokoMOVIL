package com.example.a2dam.jamp.threads;

import android.util.Log;
import android.view.View;

import com.example.a2dam.jamp.dataClasses.TelephoneBean;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.ArrayList;

public class ThreadTelephone extends Thread {
    private ArrayList<TelephoneBean> telephones;
    private int code;

    public ArrayList<TelephoneBean> getTelephones(){
        return telephones;
    }

    public ThreadTelephone(ArrayList<TelephoneBean> receivedTelephones, int i){
        this.telephones=receivedTelephones;
        this.code=i;
    }



    /**
     * Method that start the thread
     */
    @Override
    public void run() {
       switch (code){
            case 1:
                try {
                    telephones = new ArrayList<>();
                    //se conecta al servidor mongo
                    MongoClient mongoclient = MongoClients.create("mongodb://192.168.21.8:27017/jamp");
                    MongoDatabase mongoDB = mongoclient.getDatabase("jamp");
                    MongoCollection<Document> collection = mongoDB.getCollection("telephones");

                    //busca todos los campos en la coleccion y los mete en un cursor
                    FindIterable<Document> fi;
                    MongoCursor<Document> cursor;

                    fi=collection.find();
                    cursor = fi.iterator();

                    //crea un documento BSON para guardar los elementos del cursor
                    Document documento;
                    try {
                        while (cursor.hasNext()) {//si el cursor tiene contenido
                            //copia en el documento el contenido del cursor con los telefonos
                            documento = cursor.next();
                            //declara una variable de tipo telefono para copiar los campos del documento con los telefonos de la base de datos
                            TelephoneBean telephone= new TelephoneBean();
                            telephone.setName(documento.getString("name"));
                            telephone.setDescription(documento.getString("description"));
                            telephone.setTelephone(documento.getString("telephone"));

                            //añade al array el telefonos que hemos sacado del documento
                            telephones.add(telephone);
                        }
                    } finally {
                        //cierra el cursor
                        cursor.close();
                    }
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}
