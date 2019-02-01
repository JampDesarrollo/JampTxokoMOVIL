package com.example.a2dam.jamp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.dataClasses.TelephoneBean;
import com.example.a2dam.jamp.models.PrincipalActivityController;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TelephoneFragmentController.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class TelephoneFragmentController extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private EditText search;
    private ImageButton btnSearch;
    private ListView lv;
    private TextView telephoneError;
    private View view;
    private ArrayList <TelephoneBean> telephones;

    private OnFragmentInteractionListener mListener;

    public TelephoneFragmentController() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //inflar la vista para poder referenciar los elementos declarados arriba
        view = inflater.inflate(R.layout.fragment_telephone, container, false);

        //establecer el titulo del fragment en la barra superior
        ((PrincipalActivityController) getActivity()).getSupportActionBar().setTitle(R.string.fragment_telephones_titulo);

        //referenciar y escuchar el boton de busqueda
        btnSearch=view.findViewById(R.id.btnSearchTelephone);
        btnSearch.setOnClickListener(this);

        //referenciar el campo de texto de busqueda
        search=view.findViewById(R.id.tfSearchTelephoneName);

        //referenciar el campo de error
        telephoneError= view.findViewById(R.id.lblSearchTelephoneError);

        //referenciar el list view
        lv = view.findViewById(R.id.TelephoneListView);

        //cargamos los telefonos llamando al metodo de cargar telfonos
        cargarTelefonos();

        cargarLista(telephones);

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

                    search.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                }else{//si el campo esta lleno
                    //llamamos al metodo de cargar los datos con la condicional del campo de busqueda
                    cargarTelefonosCondicional();
                }
                break;
        }
    }
    //metodo que llena un arraylist con nombres y numeros de telefonos
    private void cargarTelefonos() {
        //esta seria la forma de llenar los telefonos mediante un hilo desde el servidor
        /*try {
            ThreadTelephone thread =new ThreadTelephone(1);
            //inicializar hilo
            thread.start();
            //esperar a que el hilo muera
            thread.join();
            //coger los Tehelephones que he recibido
            telephones = thread.getTelephones();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        telephones=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            //crea un telefono
            TelephoneBean tel = new TelephoneBean();
            //le carga los datos
            tel.setName("Telepizza" + " " + i);
            tel.setTelephone(String.valueOf(944644465 + i));
            //los mete en un array que usamos para mandarselo al adapter
            telephones.add(tel);
        }
    }

    private void cargarTelefonosCondicional() {
        //crea un nuevo arraylist para cargar los telefonos
        ArrayList<TelephoneBean> telephonesCondicional = new ArrayList<>();
        for (int i = 0; i < telephones.size(); i++) {
            //compara el nombre de un campo del array de telefonos con el texto del campo de busqueda
            if (telephones.get(i).getName().trim().toLowerCase().contains(search.getText().toString().trim().toLowerCase())) {//si coinciden los campos
                //si coincide mete ese telefono en otro array que se va a usar para cargar la lista
                telephonesCondicional.add(telephones.get(i));
            }
        }
        cargarLista(telephonesCondicional);

    }

    private void cargarLista(ArrayList<TelephoneBean> telephones) {
        if(telephones.isEmpty()){//si esta vacio
            //vacia el listview
            lv.setAdapter(null);
            //hace visible el campod el error
            telephoneError.setVisibility(View.VISIBLE);
        }else {//si esta lleno
            //oculta el campo del error por si estuviera visible
            telephoneError.setVisibility(View.INVISIBLE);
            //crear un nuevo tipo de dato adapterevents y pasamos el array
            AdapterTelephone adapter = new AdapterTelephone(this, telephones);
            //llamamos al setadapter del listview con el adapter que hemos creado antes
            lv.setAdapter(adapter);
            //definimos el onintemclick
            lv.setOnItemClickListener(this);

            //muestra un mensaje informativo
            Toast toast = Toast.makeText(getContext(),R.string.fragment_telephones_toast,Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//si pulsa un item de la lista
        //coje el telefono del item
        TextView tvNumber = view.findViewById(R.id.textTelephoneNum);
        //crea un uri con el telefono que ha cogido y el string telf: que hace falta para llamar a la aplicacion de llamada del dispositivo
        Uri number = Uri.parse("tel:"+ tvNumber.getText());
        //llama a la aplicacion de llamada del dispositivo y le manda el uri con el numero de telefono que hemos recojido
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
