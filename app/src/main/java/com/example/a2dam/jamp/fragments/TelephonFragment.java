package com.example.a2dam.jamp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.model.PrincipalActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TelephonFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class TelephonFragment extends Fragment implements View.OnClickListener {
    protected ImageButton btnTelefono;

    private OnFragmentInteractionListener mListener;

    public TelephonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_telephone, container, false);

        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle("Telefonos");

        btnTelefono=view.findViewById(R.id.TelepizzaCallButton);
        btnTelefono.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.TelepizzaCallButton:
                Uri number = Uri.parse(this.getResources().getString(R.string.Telepizza_Number));
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);
                break;
        }
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
