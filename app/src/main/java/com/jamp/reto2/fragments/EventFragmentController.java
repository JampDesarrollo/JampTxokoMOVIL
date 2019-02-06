package com.jamp.reto2.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jamp.reto2.R;
import com.jamp.reto2.adapters.AdapterEvents;
import com.jamp.reto2.dataClasses.EventBean;
import com.jamp.reto2.dialogs.Dialog_Go_To_Event;
import com.jamp.reto2.models.PrincipalActivityController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventFragmentController.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class EventFragmentController extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageButton btnSearch;
    private TextView eventError;
    private EditText search;
    private View view;
    private GridView lv;
    private ArrayList<EventBean> events;

    private OnFragmentInteractionListener mListener;

    public EventFragmentController() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflar la vista para poder referenciar los elementos declarados arriba
        view = inflater.inflate(R.layout.fragment_event, container, false);
        //establecer el titulo del fragment en la barra superior
        ((PrincipalActivityController) getActivity()).getSupportActionBar().setTitle(R.string.fragment_events_title);

        //referenciar y escuchar el boton de busqueda
        btnSearch=view.findViewById(R.id.btnSearchEvent);
        btnSearch.setOnClickListener(this);

        //referenciar el campo de error
        eventError=view.findViewById(R.id.lblSearchEventError);
        //referenciar el campo de texto de busqueda
        search=view.findViewById(R.id.tfSearchEvent);

        //referenciar el listview
        lv = view.findViewById(R.id.EventsListView);

        //cargamos los eventos llamando al metodo de cargarEventos
        cargarEventos();

        //lenamos la lista con los eventos que hemos recibido
        cargarLista(events);

        return view;
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSearchEvent://si clicamos en el boton de buscar
                //pinta de blanco la linea del campo de busqueda por si estuviese roja de otra busqueda
                search.setBackgroundTintList(this.getResources().getColorStateList(R.color.blanco));
                if(search.getText().toString().trim().isEmpty()){//si el campo esta vacio
                    // muestra el error del campo requerido
                    search.setError(this.getResources().getString(R.string.field_requiered_error));
                    //pinta la linea inferior del campo de busqueda de rojo
                    search.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                }else{//si el campo esta lleno
                    //llamamos al metodo de cargar los datos con la condicional del campo de busqueda
                    cargarEventosCondicional();
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Dialog_Go_To_Event dialog =new Dialog_Go_To_Event();
        dialog.show(getFragmentManager(),"Dialog_Go_To_Event");
    }

    //metodo que llena un arraylist con nombres y numeros de telefonos
    private void cargarEventos() {
        //creamos un formateador para la fecha
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        events=new ArrayList<>();
        //bucle para llenar un array con 10 eventos
        for(int i=0; i<10; i++){
            //Creamos una variable de evento
            EventBean event = new EventBean();
            //le asignamos una descripcion y un nombre de evento
            event.setDescription(getResources().getString(R.string.fragment_events_event_description) +" "+ i);
            event.setName(getResources().getString(R.string.fragment_events_event_title) + " "+ i);
            //creamos una fecha con la fecha del sistema
            java.sql.Date sql=null;
            try {
                sql = new java.sql.Date(formatter.parse(getResources().getString(R.string.fragment_event_event_date)).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //le asignamos esa fecha al evento
            event.setDate(String.valueOf(sql));
            //le asginamos un precio al evento
            event.setPrice(String.valueOf(Integer.parseInt(getResources().getString(R.string.fragment_events_event_price))+i));

            //añadimos el evento al evento
            events.add(event);
        }
    }

    private void cargarEventosCondicional() {
        //crea un nuevo arraylist para cargar los eventos que coincidan con el parametro buscado
        ArrayList<EventBean> eventosCondicional = new ArrayList<>();

        for(int i=0; i<events.size(); i++){
            //compara el nombre de un campo del array de Eventos con el texto del campo de busqueda
            if(events.get(i).getName().toLowerCase().contains(search.getText().toString().toLowerCase())){//si coincide el campo de texto con el nombre del evento
                //llena el nuevo array con los eventos
                eventosCondicional.add(events.get(i));
            }
        }
        //lena la lista con los eventos del nuevo array
        cargarLista(eventosCondicional);

    }

    //metodo que carga una lista con un arraylist
    private void cargarLista(ArrayList<EventBean> eventos) {
        if(eventos.isEmpty()) {//si el array que le mandamos esta vacio
            //vacia la lista
            lv.setAdapter(null);
            //muestra el mensaje de error
            eventError.setVisibility(View.VISIBLE);
        }else{//si la lista esta llena
            //oculta el mensaje de error
            eventError.setVisibility(View.INVISIBLE);
            //crear un nuevo tipo de dato adapterevents y pasamos el array
            AdapterEvents adapter = new AdapterEvents(this, eventos);
            //llamamos al setadapter del listview con el adapter que hemos creado antes
            lv.setAdapter(adapter);
            //definimos el onintemclick
            lv.setOnItemClickListener(this);

            //creamos y mostramos un mensaje informativo
            Toast toast = Toast.makeText(getContext(),R.string.fragment_events_event_toast,Toast.LENGTH_LONG);
            toast.show();
        }
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
