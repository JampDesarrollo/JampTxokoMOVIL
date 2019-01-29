package com.example.a2dam.jamp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.dataClasses.Event;
import com.example.a2dam.jamp.fragments.EventFragment;

import java.util.ArrayList;

public class AdapterEvents extends BaseAdapter {
    protected EventFragment activity;
    protected ArrayList<Event> items;

    public AdapterEvents(EventFragment activity, ArrayList<Event> items) {
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
    public void addAll(ArrayList<Event> events) {
        items.addAll(events);
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
            v = inf.inflate(R.layout.item_events, null);
        }
        Event dir = items.get(position);
        TextView title = v.findViewById(R.id.tfEventTitle) ,description=v.findViewById(R.id.tfEventDescription),date=v.findViewById(R.id.tfEventDate),price=v.findViewById(R.id.tfEventPrice);

        title.setText(dir.getName());
        date.setText(dir.getDate().toString());
        description.setText(dir.getDescription());
        price.setText(dir.getPrice().toString() + v.getResources().getString(R.string.country_coin));

        return v;
    }
}