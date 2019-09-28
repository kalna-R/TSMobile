package com.example.ts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    SharedPreferences sharedpreferences;

    DatabaseReference databaseReference;

    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_time_table);

        databaseReference = FirebaseDatabase.getInstance().getReference("bus");

        listViewBuses = (ListView)findViewById(R.id.listViewBuses);
        busList = new ArrayList<>();

        addBtn = (Button)findViewById(R.id.addBusBtn);

        sharedpreferences = getSharedPreferences("Pref", Context.MODE_PRIVATE);
        String usertype;
        String test = "admin@gmail.com";
        usertype = sharedpreferences.getString("user", "");

        addBtn.setVisibility(View.GONE);
        if(usertype.equals(test))
        {
            addBtn.setVisibility(View.VISIBLE);

        }
        else
        {

            addBtn.setVisibility(View.GONE);
        }

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewTimeTableActivity.this, TimeTableActivity.class);
                ViewTimeTableActivity.this.startActivity(intent);
            }
        });
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
