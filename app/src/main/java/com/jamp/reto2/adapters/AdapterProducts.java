package com.jamp.reto2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.jamp.reto2.R;
import com.jamp.reto2.dataClasses.ProductBean;
import com.jamp.reto2.fragments.ProductFragmentController;

import java.util.ArrayList;

public class AdapterProducts extends BaseAdapter {
    protected ProductFragmentController activity;
    protected ArrayList<ProductBean> items;

    //inicializar las variables declaradas arriba
    public AdapterProducts(ProductFragmentController activity, ArrayList<ProductBean> items) {
        this.activity = activity;
        this.items = items;
    }

    //metodo para calcular la longitud del array
    @Override
    public int getCount() {
        return items.size();
    }

    //obtener el elemento mediante un argumento
    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    //metodo para recoger el id del elemento
    @Override
    public long getItemId(int position) {
        return position;
    }


    //metodo para llenar la lista mediante los valores que se le pasan del array
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null) {//si la vista esta vacia la inflamos con el activity del fragment
            LayoutInflater inf = (LayoutInflater) activity.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_products, null);
        }

        //creamos una  variable de tipo producto y la  inicializamos con la posicion que le hemos mandado del array
        ProductBean product=items.get(position);
        //declaramos y referenciamos los textview del titulo y el precio del producto seleccionado
        TextView title=v.findViewById(R.id.textProduct_Name), price=v.findViewById(R.id.textProductPrice);
        //en esos campos introducimos los datos que hemos recibido del array y hemos guardado en la variable de productos
        title.setText(product.getName());
        price.setText(String.valueOf(product.getPrice()));

        return v;
    }
}