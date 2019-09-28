package com.example.ts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewTimeTableActivity extends AppCompatActivity {

    ListView listViewBuses;
    List<Bus> busList;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_time_table);

        databaseReference = FirebaseDatabase.getInstance().getReference("bus");

        listViewBuses = (ListView)findViewById(R.id.listViewBuses);
        busList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                busList.clear();

                for (DataSnapshot busSnapShot : dataSnapshot.getChildren()) {
                    Bus bus = busSnapShot.getValue(Bus.class);
                    busList.add(bus);
                }

                BusesList adapter = new BusesList(ViewTimeTableActivity.this, busList);
                listViewBuses.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
