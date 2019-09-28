package com.example.ts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    ListView listViewTickets;

    List<Ticket> ticketList;

//    SharedPreferences sharedpreferences;
//    TextView balanceTextView;
//    DatabaseReference databaseTicketing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("ticket");

        listViewTickets = (ListView)findViewById(R.id.listViewTickets);
        ticketList = new ArrayList<>();

//        balanceTextView = (TextView)findViewById(R.id.balance);
//        databaseTicketing = FirebaseDatabase.getInstance().getReference("ticket");
//        sharedpreferences = getSharedPreferences("Pref", Context.MODE_PRIVATE);
//        balanceTextView.setText(sharedpreferences.getString("Balance", ""));
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.orderByChild("email").equalTo("vimanga").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ticketList.clear();

                for(DataSnapshot ticketSnapShot : dataSnapshot.getChildren()){
                    Ticket ticket = ticketSnapShot.getValue(Ticket.class);
                    ticketList.add(ticket);
                }

                TicketsList adapter = new TicketsList(ProfileActivity.this, ticketList);
                listViewTickets.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
