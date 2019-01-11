package com.example.a2dam.jamp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.dataClasses.Telephone;
import com.example.a2dam.jamp.fragments.TelephoneFragment;

import java.util.ArrayList;
public class AdapterTelephone extends BaseAdapter {
    protected TelephoneFragment activity;
    protected ArrayList<Telephone> items;
    public AdapterTelephone(TelephoneFragment activity, ArrayList<Telephone> items) {
        this.activity = activity;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }
    public void clear() {
        items.clear();
    }
    public void addAll(ArrayList<Telephone> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }
    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.telephone_item, null);
        }
        Telephone dir = items.get(position);
        TextView title = v.findViewById(R.id.textTelephoneName);
        title.setText(dir.getNombre());
        TextView description = v.findViewById(R.id.textTelephoneNum);
        description.setText(dir.getTelephon().toString());
        return v;
    }
}