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
import com.example.a2dam.jamp.models.PrincipalActivity;


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
    TextView telephoneError;
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

        search=view.findViewById(R.id.tfSearchTelephoneName);
        //referenciar el listview
        telephoneError= view.findViewById(R.id.lblSearchTelephoneError);

        ArrayList<Telephone> telephones = cargarTelefonos();
        if(telephones.isEmpty()) {
            telephoneError.setVisibility(View.VISIBLE);
        }else{
            lv = view.findViewById(R.id.TelephoneListView);
            //crear un nuevo tipo de dato adapterevents y pasamos el array
            AdapterTelephone adapter = new AdapterTelephone(this, telephones);
            //llamamos al setadapter del listview con el adapter que hemos creado antes
            lv.setAdapter(adapter);
            //definimos el onintemclick
            lv.setOnItemClickListener(this);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSearchTelephone:
                search.setBackgroundTintList(this.getResources().getColorStateList(R.color.blanco));
                if(search.getText().toString().trim().isEmpty()){
                    search.setError(this.getResources().getString(R.string.field_requiered_error));
                    search.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                }else{
                    cargarTelefonosCondicional();
                }
                break;
        }
    }

    private ArrayList<Telephone> cargarTelefonos() {
        ArrayList<Telephone> telephone = new ArrayList<>();
        for(int i=0; i<20; i++){
            Telephone tel = new Telephone();

            tel.setNombre("Telepizza" +" "+i);
            tel.setTelephon(944644465 + i);

            telephone.add(tel);
        }
        return telephone;
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
