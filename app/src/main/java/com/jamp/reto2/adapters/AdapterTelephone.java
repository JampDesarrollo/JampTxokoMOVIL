package com.jamp.reto2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jamp.reto2.R;
import com.jamp.reto2.dataClasses.TelephoneBean;
import com.jamp.reto2.fragments.TelephoneFragmentController;

import java.util.ArrayList;
public class AdapterTelephone extends BaseAdapter {
    protected TelephoneFragmentController activity;
    protected ArrayList<TelephoneBean> items;

    //inicializar las variables declaradas arriba
    public AdapterTelephone(TelephoneFragmentController activity, ArrayList<TelephoneBean> items) {
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
            v = inf.inflate(R.layout.item_telephone, null);
        }
        //creamos una  variable de tipo Telephone y la  inicializamos con la posicion que le hemos mandado del array
        TelephoneBean dir = items.get(position);
        //declaramos y referenciamos los textview del titulo y la descripcion del telefono seleccionado
        TextView title = v.findViewById(R.id.textTelephoneName);
        title.setText(dir.getName());
        TextView description = v.findViewById(R.id.textTelephoneNum);
        description.setText(String.valueOf(dir.getTelephone()));

        return v;
    }
}