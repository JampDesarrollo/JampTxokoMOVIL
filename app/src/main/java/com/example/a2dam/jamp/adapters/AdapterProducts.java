package com.example.a2dam.jamp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.dataClasses.Product;
import com.example.a2dam.jamp.fragments.ProductFragment;

import java.util.ArrayList;

public class AdapterProducts extends BaseAdapter {
    protected ProductFragment activity;
    protected ArrayList<Product> items;

    //inicializar las variables declaradas arriba
    public AdapterProducts(ProductFragment activity, ArrayList<Product> items) {
        this.activity = activity;
        this.items = items;
    }

    //metodo para calcular la longitud del array
    @Override
    public int getCount() {
        return items.size();
    }

    //metodo para vaciar el arraylist
    public void clear() {
        items.clear();
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
        Product product=items.get(position);
        //declaramos y referenciamos los textview del titulo y el precio del producto seleccionado
        TextView title=v.findViewById(R.id.textProduct_Name), price=v.findViewById(R.id.textProductPrice);
        //en esoscampos introducimos los datos que hemos recibido del array y hemos guardado en la variable de productos
        title.setText(product.getName().toString());
        price.setText(product.getPrice().toString());

        return v;
    }
}