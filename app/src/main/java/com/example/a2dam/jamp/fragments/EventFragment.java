package com.example.a2dam.jamp.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.adapters.AdapterEvents;
import com.example.a2dam.jamp.dataClasses.Event;
import com.example.a2dam.jamp.model.PrincipalActivity;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class EventFragment extends Fragment {
    private GridView lv;
    private OnFragmentInteractionListener mListener;

    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_event, container, false);

        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle(R.string.fragment_events_title);

        ArrayList<Event> events = new ArrayList<>();

        for(int i=0; i<5; i++){
            Event event = new Event();

            event.setDescription(getResources().getString(R.string.fragment_events_event_description) + i);
            event.setName(getResources().getString(R.string.fragment_events_event_title) + i);
            //event.setDate(Date.valueOf(getResources().getString(R.string.fragment_event_event_date)));
            //event.setPrice(Integer.valueOf(getResources().getString(R.string.fragment_events_event_price)));


            events.add(event);
        }


        lv = view.findViewById(R.id.EventsListView);
        AdapterEvents adapter = new AdapterEvents(this, events);
        lv.setAdapter(adapter);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

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
