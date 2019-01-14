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
import com.example.a2dam.jamp.dataClasses.Telephone;
import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.model.PrincipalActivity;


import java.util.ArrayList;
import java.util.logging.Logger;


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
    private View view;

    private OnFragmentInteractionListener mListener;

    public TelephoneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_telephone, container, false);
        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle(R.string.fragment_telephones_titulo);

        btnSearch=view.findViewById(R.id.btnSearchTelephone);
        btnSearch.setOnClickListener(this);

        search=view.findViewById(R.id.tfSearchTelephone);

        ArrayList<Telephone> telephone = cargarTelefonos();

        lv = view.findViewById(R.id.TelephoneListView);
        AdapterTelephone adapter = new AdapterTelephone(this, telephone);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

        return view;
    }

    private ArrayList<Telephone> cargarTelefonos() {
        ArrayList<Telephone> telephone = new ArrayList<>();
        for(int i=0; i<20; i++){
            Telephone tel = new Telephone();

            tel.setNombre("Telepizza" +i);
            tel.setTelephon(944644465 + i);

            telephone.add(tel);
        }
        return telephone;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSearchTelephone:
                if(search.getText().toString().trim().isEmpty()){
                    search.setError(this.getResources().getString(R.string.field_requiered_error));
                }else{
                    cargarEventosCondicional();
                    Toast toast = Toast.makeText(getContext(),R.string.fragment_events_event_toast,Toast.LENGTH_LONG);
                    toast.show();
                }
                break;
        }
    }

    private void cargarEventosCondicional() {
        ArrayList<Telephone> telephone = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Telephone tel = new Telephone();

            tel.setNombre("Telepizza" + i);
            tel.setTelephon(944644465);

            if (tel.getNombre().trim().toLowerCase().contains(search.getText().toString().trim().toLowerCase())) {
                telephone.add(tel);
            }
        }
        AdapterTelephone adapter = new AdapterTelephone(this, telephone);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView tvNumber = view.findViewById(R.id.textTelephoneNum);
        Uri number = Uri.parse("tel:"+ tvNumber.getText());
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
