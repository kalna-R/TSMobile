package com.example.ts;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;

public class TicketsList extends ArrayAdapter<Ticket> {

    private Activity context;
    private List<Ticket> ticketsList;

    public TicketsList(Activity context, List<Ticket> ticketsList){
        super(context, R.layout.list_layout, ticketsList);
        this.context = context;
        this.ticketsList = ticketsList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //        return super.getView(position, convertView, parent);

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout, null,true);

        TextView textViewFrom = (TextView)listViewItem.findViewById(R.id.textViewFrom);
        TextView textViewTo = (TextView)listViewItem.findViewById(R.id.textViewTo);
//        TextView textViewDate = (TextView)listViewItem.findViewById(R.id.textViewDate);

        Ticket ticket = ticketsList.get(position);

        textViewFrom.setText(ticket.getOrigin());
        textViewTo.setText(ticket.getDestination());
//        textViewDate.setText(ticket.getDate());

        return listViewItem;
    }
}
