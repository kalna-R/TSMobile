package com.example.ts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class PassengerCountActivity extends AppCompatActivity {

    TextView countview;
    SharedPreferences sharedpreferences;
    String countNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_count);

        countview = (TextView)findViewById(R.id.count);

        sharedpreferences = getSharedPreferences("Pref", Context.MODE_PRIVATE);

        countNo = sharedpreferences.getString("count", "");

        countview.setText(countNo);
    }
}
