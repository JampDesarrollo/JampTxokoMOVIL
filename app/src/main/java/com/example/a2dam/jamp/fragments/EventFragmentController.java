package com.example.a2dam.jamp.fragments;

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

import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.adapters.AdapterEvents;
import com.example.a2dam.jamp.dataClasses.EventBean;
import com.example.a2dam.jamp.dialogs.Dialog_Go_To_Event;
import com.example.a2dam.jamp.models.PrincipalActivityController;

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
        view = inflater.inflate(R.layout.fragment_event, container, false);
        ((PrincipalActivityController) getActivity()).getSupportActionBar().setTitle(R.string.fragment_events_title);

        btnSearch=view.findViewById(R.id.btnSearchEvent);
        btnSearch.setOnClickListener(this);

        eventError=view.findViewById(R.id.lblSearchEventError);
        search=view.findViewById(R.id.tfSearchEvent);

        //referenciar el listview
        lv = view.findViewById(R.id.EventsListView);

        cargarEventos();

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
            case R.id.btnSearchEvent:
                search.setBackgroundTintList(this.getResources().getColorStateList(R.color.blanco));
                if(search.getText().toString().trim().isEmpty()){
                    search.setError(this.getResources().getString(R.string.field_requiered_error));
                    search.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                }else{
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

    private void cargarEventos() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        for(int i=0; i<10; i++){
            EventBean event = new EventBean();
            event.setDescription(getResources().getString(R.string.fragment_events_event_description) +" "+ i);
            event.setName(getResources().getString(R.string.fragment_events_event_title) + " "+ i);
            java.sql.Date sql=null;
            try {
                sql = new java.sql.Date(formatter.parse(getResources().getString(R.string.fragment_event_event_date)).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            event.setDate(String.valueOf(sql));
            event.setPrice(String.valueOf(Integer.parseInt(getResources().getString(R.string.fragment_events_event_price))+i));

            events.add(event);
        }
    }

    private void cargarEventosCondicional() {
        //crea un nuevo arraylist para cargar los eventos
        ArrayList<EventBean> eventosCondicional = new ArrayList<>();

        for(int i=0; i<events.size(); i++){
            //compara el nombre de un campo del array de Eventos con el texto del campo de busqueda
            if(events.get(i).getName().toLowerCase().contains(search.getText().toString().toLowerCase())){
                eventosCondicional.add(events.get(i));
            }
        }
        cargarLista(eventosCondicional);

    }

    private void cargarLista(ArrayList<EventBean> eventos) {
        if(eventos.isEmpty()) {
            lv.setAdapter(null);
            eventError.setVisibility(View.VISIBLE);
        }else{
            eventError.setVisibility(View.INVISIBLE);
            //crear un nuevo tipo de dato adapterevents y pasamos el array
            AdapterEvents adapter = new AdapterEvents(this, eventos);
            //llamamos al setadapter del listview con el adapter que hemos creado antes
            lv.setAdapter(adapter);
            //definimos el onintemclick
            lv.setOnItemClickListener(this);
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
