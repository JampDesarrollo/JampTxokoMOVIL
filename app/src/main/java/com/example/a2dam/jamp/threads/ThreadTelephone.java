package com.example.a2dam.jamp.threads;

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
    private int code;
    private ArrayList<TelephoneBean>telephones;

    //metodo que devuleve el array con los telefonos que hemos conseguido de la base de datos
    public ArrayList<TelephoneBean> getTelephones() {
        return this.telephones;
    }
    //metodo que inicializa el int code con el que le mandamos del TelephoneFragmentController
    public ThreadTelephone(int i){
        this.code=i;
    }

    /**
     * Method that start the thread
     */
    @Override
    public void run() {
        switch (code){
            case 1://si le hemos mandado el numero 1
                try {
                    //se conecta al servidor mongo
                    MongoClient mongoclient = MongoClients.create("mongodb://192.168.21.38:27017/jamp");
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
                }catch (Exception e){//si casca
                    // tira una RuntimeException con el nombre de la excepcion que ha habido
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}
