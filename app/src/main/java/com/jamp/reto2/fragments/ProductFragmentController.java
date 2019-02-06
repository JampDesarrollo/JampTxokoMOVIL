package com.jamp.reto2.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.jamp.reto2.adapters.AdapterProducts;
import com.jamp.reto2.dataClasses.ProductBean;
import com.jamp.reto2.dialogs.Dialog_Product;
import com.jamp.reto2.models.PrincipalActivityController;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductFragmentController.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ProductFragmentController extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private EditText search;
    private TextView productError;
    private ImageButton btnSearch;
    private View view;
    private GridView lv;
    private ArrayList<ProductBean> products;

    private OnFragmentInteractionListener mListener;

    public ProductFragmentController() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflar la vista para poder referenciar los elementos declarados arriba
        view = inflater.inflate(R.layout.fragment_product, container, false);

        //establecer el titulo del fragment en la barra superior
        ((PrincipalActivityController) getActivity()).getSupportActionBar().setTitle(R.string.fragment_products_title);

        //referenciar y escuchar el boton de busqueda
        btnSearch=view.findViewById(R.id.btnSearchProduct);
        btnSearch.setOnClickListener(this);

        //referenciar el campo de texto de busqueda
        search=view.findViewById(R.id.tfSearchProduct);

        //referenciar el campo de error
        productError=view.findViewById(R.id.lblSearchProductError);

        //referenciar el listview
        lv = view.findViewById(R.id.ProductGridView);

        //cargamos los productos llamando al metodo de cargarproductos
        cargarProductos();

        //llenamos la lista con los productos
        cargarLista(products);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSearchProduct://si clicamos en el boton de buscar
                //pinta de blanco la linea del campo de busqueda por si estuviese roja de otra busqueda
                search.setBackgroundTintList(this.getResources().getColorStateList(R.color.blanco));
                if(search.getText().toString().trim().isEmpty()){//si el campo esta vacio
                    // muestra el error del campo requerido
                    search.setError(this.getResources().getString(R.string.field_requiered_error));
                    //pinta la linea inferior del campo de busqueda de rojo
                    search.setBackgroundTintList(this.getResources().getColorStateList(R.color.rojo));
                }else{//si el campo esta lleno
                    //llamamos al metodo de cargar los datos con la condicional del campo de busqueda
                    cargarProductosCondicional();
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //llena un bundle con los datos del producto(nombre,descripcion,precio)
        Bundle datosProducto=new Bundle();
        datosProducto.putString("nombre",products.get(position).getName());
        datosProducto.putString("descripcion",products.get(position).getDescription());
        datosProducto.putInt("precio",Integer.valueOf(products.get(position).getPrice()));

        //crea y llama a un fragment nuevo pasandole el bundle que hemos creado arriba
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

    private void cargarProductos() {
        //metodo para cargar los productos con los datos del servidor
        /*
        try{
            ProductLogic logic=ILogicFactory.getProductLogic();
            products=logic.findAllProducts();
        } catch (BusinessLogicException e) {
            e.printStackTrace();
        }*/

        //carga un arraylist con 20 productos
        products=new ArrayList<>();
        for(int i=0; i<20; i++){
            ProductBean prod = new ProductBean();

            prod.setDescription(getResources().getString(R.string.fragment_products_description)+ i);
            prod.setName(getResources().getString(R.string.fragment_products_product) + i);
            prod.setPrice(String.valueOf(i+1));

            products.add(prod);
        }
    }

    private void cargarProductosCondicional() {
        //crea un nuevo arraylist para cargar los productos
        ArrayList<ProductBean>productsCondicional =new ArrayList<>();
        for(int i=0; i<products.size(); i++){
            if(products.get(i).getName().trim().toLowerCase().contains(search.getText().toString().toLowerCase())) {//si coincide el campo de texto con el nombre del producto
                //añade el producto al nuevo arraylist
                productsCondicional.add(products.get(i));
            }
        }
        //carga la lista con el nuevo arraylist
        cargarLista(productsCondicional);
    }

    private void cargarLista(ArrayList<ProductBean> products) {
        if(products.isEmpty()) {//si esta vacio
            //vacia la lista
            lv.setAdapter(null);
            //muestra el campo de error
            productError.setVisibility(View.VISIBLE);
        }else{//si esta lleno
            //oculta el campo de error
            productError.setVisibility(View.INVISIBLE);
            //crear un nuevo tipo de dato adapterevents y pasamos el array
            AdapterProducts adapter = new AdapterProducts(this, products);
            //llamamos al setadapter del listview con el adapter que hemos creado antes
            lv.setAdapter(adapter);
            //definimos el onintemclick
            lv.setOnItemClickListener(this);
            //muestra un mensaje informativo
            Toast toast = Toast.makeText(getContext(),R.string.fragment_products_product_toast,Toast.LENGTH_LONG);
            toast.show();
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
