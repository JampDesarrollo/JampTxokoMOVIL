package com.example.a2dam.jamp.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

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
public class ProductFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private EditText search;
    private ImageButton btnSearch;
    private View view;
    private GridView lv;
    private ArrayList<Product> products;

    private OnFragmentInteractionListener mListener;

    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product, container, false);

        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle(R.string.fragment_products_title);

        btnSearch=view.findViewById(R.id.btnSearchProduct);
        btnSearch.setOnClickListener(this);

        search=view.findViewById(R.id.tfSearchProduct);

        products = cargarProductos();


        lv = view.findViewById(R.id.ProductGridView);
        AdapterProducts adapter = new AdapterProducts(this, products);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSearchProduct:
                if(search.getText().toString().trim().isEmpty()){
                    search.setError(this.getResources().getString(R.string.field_requiered_error));
                    search.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                }else{
                    cargarEventosCondicional();
                    Toast toast = Toast.makeText(getContext(),R.string.fragment_products_product_toast,Toast.LENGTH_LONG);
                    toast.show();
                }
                break;
        }
    }

    private ArrayList<Product> cargarProductos() {
        products =new ArrayList<>();
        for(int i=0; i<20; i++){
            Product prod = new Product();

            prod.setDescription(getResources().getString(R.string.fragment_products_description)+ i);
            prod.setName(getResources().getString(R.string.fragment_products_product) + i);
            prod.setPrice((float) (1+i));

            products.add(prod);
        }
        return products;
    }

    private void cargarEventosCondicional() {
        products =new ArrayList<>();
        for(int i=0; i<20; i++){
            Product prod = new Product();

            prod.setDescription(getResources().getString(R.string.fragment_products_description)+ i);
            prod.setName(getResources().getString(R.string.fragment_products_product) + i);
            prod.setPrice((float) (1+i));
            if(prod.getName().trim().toLowerCase().contains(search.getText().toString().toLowerCase())) {
                products.add(prod);
            }
        }
        AdapterProducts adapter = new AdapterProducts(this, products);
        lv.setAdapter(adapter);
        lv.setEnabled(true);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle datosProducto=new Bundle();
        datosProducto.putString("nombre",products.get(position).getName());
        datosProducto.putString("descripcion",products.get(position).getDescription());
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        Dialog_Product dialog_Product= new Dialog_Product();
        dialog_Product.setArguments(datosProducto);
        fragmentTransaction.add(R.id.fragment, dialog_Product);
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
