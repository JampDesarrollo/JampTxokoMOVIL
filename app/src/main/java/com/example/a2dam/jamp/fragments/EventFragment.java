package com.example.a2dam.jamp.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.adapters.AdapterEvents;
import com.example.a2dam.jamp.dataClasses.Event;
import com.example.a2dam.jamp.dialogs.Dialog_Go_To_Event;
import com.example.a2dam.jamp.model.PrincipalActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class EventFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageButton btnSearch;
    private EditText search;
    private View view;
    private GridView lv;

    private OnFragmentInteractionListener mListener;

    public EventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_event, container, false);
        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle(R.string.fragment_events_title);

        btnSearch=view.findViewById(R.id.btnSearchEvent);
        btnSearch.setOnClickListener(this);

        search=view.findViewById(R.id.tfSearchEvent);

        ArrayList<Event> events=cargarEventos();


        lv = view.findViewById(R.id.EventsListView);
        AdapterEvents adapter = new AdapterEvents(this, events);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
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
                if(search.getText().toString().trim().isEmpty()){
                    search.setError(this.getResources().getString(R.string.field_requiered_error));
                    search.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                }else{
                    cargarEventosCondicional();
                    Toast toast = Toast.makeText(getContext(),R.string.fragment_events_event_toast,Toast.LENGTH_LONG);
                    toast.show();
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Dialog_Go_To_Event dialog =new Dialog_Go_To_Event();
        dialog.show(getFragmentManager(),"Dialog_Go_To_Event");
    }

    private ArrayList<Event> cargarEventos() {
        ArrayList<Event> events = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


        for(int i=0; i<5; i++){
            Event event = new Event();
            event.setDescription(getResources().getString(R.string.fragment_events_event_description) +" "+ i);
            event.setName(getResources().getString(R.string.fragment_events_event_title) + " "+ i);
            java.sql.Date sql=null;
            try {
                sql = new java.sql.Date(formatter.parse(getResources().getString(R.string.fragment_event_event_date)).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            event.setDate(sql);
            event.setPrice(Integer.parseInt(getResources().getString(R.string.fragment_events_event_price))+i);

            events.add(event);
        }
        return events;
    }

    private void cargarEventosCondicional() {
        ArrayList<Event> events = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        for(int i=0; i<5; i++){
            Event event = new Event();
            event.setDescription(getResources().getString(R.string.fragment_events_event_description) +" "+ i);
            event.setName(getResources().getString(R.string.fragment_events_event_title) + " "+ i);
            java.sql.Date sql=null;
            try {
                sql = new java.sql.Date(formatter.parse(getResources().getString(R.string.fragment_event_event_date)).getTime()+i);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            event.setDate(sql);
            event.setPrice(Integer.parseInt(getResources().getString(R.string.fragment_events_event_price))+i);
            if(event.getName().toLowerCase().contains(search.getText().toString().toLowerCase())){
                events.add(event);
            }

            lv = view.findViewById(R.id.EventsListView);
            AdapterEvents adapter = new AdapterEvents(this, events);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(this);
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
