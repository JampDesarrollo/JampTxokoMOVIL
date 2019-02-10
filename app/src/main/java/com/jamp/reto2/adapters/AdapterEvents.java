package com.jamp.reto2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.jamp.reto2.R;
import com.jamp.reto2.dataClasses.EventBean;
import com.jamp.reto2.fragments.EventFragmentController;

import java.util.ArrayList;

public class AdapterEvents extends BaseAdapter {
    protected EventFragmentController activity;
    protected ArrayList<EventBean> items;

    //inicializar las variables declaradas arriba
    public AdapterEvents(EventFragmentController activity, ArrayList<EventBean> items) {
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
            v = inf.inflate(R.layout.item_events, null);
        }
        //creamos una  variable de tipo Event y la  inicializamos con la posicion que le hemos mandado del array
        EventBean dir = items.get(position);
        //declaramos y referenciamos los textview evento seleccionado
        TextView title = v.findViewById(R.id.tfEventTitle) ,description=v.findViewById(R.id.tfEventDescription),date=v.findViewById(R.id.tfEventDate),price=v.findViewById(R.id.tfEventPrice);

        //en esos campos introducimos los datos que hemos recibido del array y hemos guardado en la variable dir
        title.setText(dir.getName());
        date.setText(dir.getDate());
        description.setText(dir.getDescription());
        String precio=String.valueOf(dir.getPrice()) + v.getResources().getString(R.string.country_coin);
        price.setText(precio);

        return v;
    }
}