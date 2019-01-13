package com.example.a2dam.jamp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.adapters.AdapterProducts;
import com.example.a2dam.jamp.dataClasses.Product;
import com.example.a2dam.jamp.dialogs.Dialog_Product;
import com.example.a2dam.jamp.model.PrincipalActivity;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ProductFragment extends Fragment implements View.OnClickListener {
    protected Button btnProductos; 

    private OnFragmentInteractionListener mListener;

    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_product, container, false);

        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle(R.string.fragment_products_title);

        ArrayList<Product> products = new ArrayList<>();

        for(int i=0; i<20; i++){
            Product prod = new Product();

            prod.setDescription(getResources().getString(R.string.fragment_products_title));
            prod.setName(getResources().getString(R.string.fragment_products_product));
            prod.setPrice(Float.valueOf(getResources().getString(R.string.fragment_products_price)));


            products.add(prod);
        }


        ListView lv = view.findViewById(R.id.ProductGridLayout);
        AdapterProducts adapter = new AdapterProducts(this, products);
        lv.setAdapter(adapter);

        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle(R.string.fragment_telephones_titulo);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tvNumber = view.findViewById(R.id.textTelephoneNum);
                Uri number = Uri.parse("tel:"+ tvNumber.getText());
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);

//CODIGO AQUI
            }
        });

        return view;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //btnProductos=view.findViewById(R.id.buttonProduct);
        //btnProductos.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        /*if(v.getId()==R.id.buttonProduct){
            ok();
        }*/
    }
    public void ok(){
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        fragmentManager= getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        Dialog_Product Dialog_Product= new Dialog_Product();
        fragmentTransaction.add(R.id.fragment, Dialog_Product);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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
