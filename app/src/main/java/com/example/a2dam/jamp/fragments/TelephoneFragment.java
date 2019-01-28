package com.example.a2dam.jamp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a2dam.jamp.adapters.AdapterTelephone;
import com.example.a2dam.jamp.dataClasses.Telephone;
import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.models.PrincipalActivity;
import com.example.a2dam.jamp.threads.ThreadForSocketTelephone;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import org.bson.Document;
import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TelephoneFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class TelephoneFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private EditText search;
    private ImageButton btnSearch;
    private ListView lv;
    private TextView telephoneError;
    private View view;
    private static MongoClient mongoclient;
    private static MongoDatabase mongoDB;
    private static MongoCollection<Document> collection;

    private OnFragmentInteractionListener mListener;

    public TelephoneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //inflar la vista para poder referenciar los elementos declarados arriba
        view = inflater.inflate(R.layout.fragment_telephone, container, false);

        //establecer el titulo del fragment en la barra superior
        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle(R.string.fragment_telephones_titulo);

        //referenciar y escuchar el boton de busqueda
        btnSearch=view.findViewById(R.id.btnSearchTelephone);
        btnSearch.setOnClickListener(this);

        //referenciar el campo de texto de busqueda
        search=view.findViewById(R.id.tfSearchTelephoneName);

        //referenciar el listview
        telephoneError= view.findViewById(R.id.lblSearchTelephoneError);

        //declaramos un arraylist de telefonos y lo inicializamos llamando al metodo de cargar telfonos
        ArrayList<Telephone> telephones = cargarTelefonos();


        if(telephones.isEmpty()) {//si el metodo cargar telefonos devuelve un array vacio
            //se hace visible un mesaje de error oculto
            telephoneError.setVisibility(View.VISIBLE);
        }else{//si el array esta lleno
            //referenciar el list view
            lv = view.findViewById(R.id.TelephoneListView);
            //crear un nuevo tipo de dato adapterevents y lo inicializamos con el mismo y el array que tenemos lleno
            AdapterTelephone adapter = new AdapterTelephone(this, telephones);
            //llamamos al setadapter del listview con el adapter que hemos creado antes para llenar el listview con todos los items del array
            lv.setAdapter(adapter);
            //definimos el onintemclick
            lv.setOnItemClickListener(this);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSearchTelephone://si clicamos en el boton de buscar
                //pinta de blanco la linea del campo de busqueda por si estuviese roja de otra busqueda
                search.setBackgroundTintList(this.getResources().getColorStateList(R.color.blanco));
                if(search.getText().toString().trim().isEmpty()){//si el campo esta vacio
                    // muestra el error del campo requerido
                    search.setError(this.getResources().getString(R.string.field_requiered_error));
                    //pinta la linea inferior del campo de busqueda de rojo
                    search.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                }else{//si el campo esta lleno
                    //llamamos al metodo de cargar los datos con la condicional del campo de busqueda
                    cargarTelefonosCondicional();
                }
                break;
        }
    }

    private ArrayList<Telephone> cargarTelefonos() {
        ArrayList <Telephone> telephones=new ArrayList<>();


        //Declarar all por separado
        mongoclient = MongoClients.create("mongodb://192.168.0.15:27017/jamp");
        mongoDB = mongoclient.getDatabase("jamp");
        collection = mongoDB.getCollection("telephones");

        FindIterable<Document> fi;
        MongoCursor<Document> cursor;
        fi = collection.find();
        cursor = fi.iterator();
        Document documento;

        Telephone tel = null;
        try {
            if(telephones.isEmpty()) {
                lv.setAdapter(null);
                telephoneError.setVisibility(View.VISIBLE);
            }
            while (cursor.hasNext()) {
                documento = cursor.next();
                //crear un documeto para que no carge el siguiente
                tel.setId(documento.getInteger("id"));
                tel.setDescription(cursor.next().getString("descripción"));
                tel.setNombre(cursor.next().getString("name"));
                tel.setTelephon(cursor.next().getInteger("telephone"));
                telephones.add(tel);
            }
        }catch(Exception e) {
            Log.d("error","la exception: "+ e);
        } finally {
            cursor.close();
        }

        /*
        for (int i = 0; i < 20; i++) {
            Telephone tel = new Telephone();

            tel.setNombre("Telepizza" + " " + i);
            tel.setTelephon(944644465);

            telephones.add(tel);

        }*/

        return telephones;
    }

    private void cargarTelefonosCondicional() {
        ArrayList<Telephone> telephones = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Telephone tel = new Telephone();

            tel.setNombre("Telepizza" +" "+ i);
            tel.setTelephon(944644465);

            if (tel.getNombre().trim().toLowerCase().contains(search.getText().toString().trim().toLowerCase())) {
                telephones.add(tel);
            }
        }
        if(telephones.isEmpty()){
            lv.setAdapter(null);
            telephoneError.setVisibility(View.VISIBLE);
        }else {
            telephoneError.setVisibility(View.INVISIBLE);
            //crear un nuevo tipo de dato adapterevents y pasamos el array
            AdapterTelephone adapter = new AdapterTelephone(this, telephones);
            //llamamos al setadapter del listview con el adapter que hemos creado antes
            lv.setAdapter(adapter);
            //definimos el onintemclick
            lv.setOnItemClickListener(this);

            Toast toast = Toast.makeText(getContext(),R.string.fragment_events_event_toast,Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView tvNumber = view.findViewById(R.id.textTelephoneNum);
        Uri number = Uri.parse("tel:"+ tvNumber.getText());
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
