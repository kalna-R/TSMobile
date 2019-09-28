package com.example.ts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PurchaseTokenActivity extends AppCompatActivity {

    Button buyBtn, qrbtn, profile, pcount, qrbtn2, timeTableBtn;

    ListView listViewTickets;

    List<Ticket> ticketList;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_token);

        databaseReference = FirebaseDatabase.getInstance().getReference("ticket");

        buyBtn = (Button)findViewById(R.id.buyTokenBtn);
        qrbtn = (Button)findViewById(R.id.qrbtn);
        qrbtn2 = (Button)findViewById(R.id.qrbtn2);
        profile = (Button)findViewById(R.id.myprofile);
        pcount = (Button)findViewById(R.id.pcount);
        timeTableBtn = (Button)findViewById(R.id.timeTable);

        listViewTickets = (ListView)findViewById(R.id.listViewTickets);

        ticketList = new ArrayList<>();

        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PurchaseTokenActivity.this, TokenTypeActivity.class);
                PurchaseTokenActivity.this.startActivity(intent);

            }
        });

        qrbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PurchaseTokenActivity.this, QRScannerActivity.class);
                PurchaseTokenActivity.this.startActivity(intent);

            }
        });

        qrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PurchaseTokenActivity.this, QRScannerActivity.class);
                PurchaseTokenActivity.this.startActivity(intent);

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PurchaseTokenActivity.this, ProfileActivity.class);
                PurchaseTokenActivity.this.startActivity(intent);

            }
        });

        pcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PurchaseTokenActivity.this, PassengerCountActivity.class);
                PurchaseTokenActivity.this.startActivity(intent);

            }
        });

        timeTableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PurchaseTokenActivity.this, TimeTableActivity.class);
                PurchaseTokenActivity.this.startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ticketList.clear();

                for(DataSnapshot ticketSnapShot : dataSnapshot.getChildren()){
                    Ticket ticket = ticketSnapShot.getValue(Ticket.class);
                    ticketList.add(ticket);
                }

                TicketsList adapter = new TicketsList(PurchaseTokenActivity.this, ticketList);
                listViewTickets.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }





}
