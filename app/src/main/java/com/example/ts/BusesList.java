package com.example.ts;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;

public class BusesList extends ArrayAdapter<Bus> {

    private Activity context;
    private List<Bus> busesList;

    public BusesList(Activity context, List<Bus> busesList){
        super(context, R.layout.listbusses_layout, busesList);
        this.context = context;
        this.busesList = busesList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.listbusses_layout, null,true);

        TextView textViewFrom = (TextView)listViewItem.findViewById(R.id.textViewFrom);
        TextView textViewTo = (TextView)listViewItem.findViewById(R.id.textViewTo);
        TextView textViewRoute = (TextView)listViewItem.findViewById(R.id.textViewRoute11);
        TextView time = (TextView)listViewItem.findViewById(R.id.textViewTime11);

        Bus bus = busesList.get(position);

        textViewFrom.setText(bus.getOrigin());
        textViewTo.setText(bus.getDestination());
        textViewRoute.setText(bus.getRoute());
        time.setText(bus.getTime());

        return listViewItem;
    }
}
