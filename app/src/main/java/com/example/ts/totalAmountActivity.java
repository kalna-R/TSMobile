package com.example.ts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class totalAmountActivity extends AppCompatActivity {

    Button nextBtn;
    TextView adultsTextView, seniorTextView, childrenTextView, fromTextView, toTextView, date, time;

    SharedPreferences sharedpreferences;

    ArrayList list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_amount);

        nextBtn = (Button)findViewById(R.id.totalNextButton);
        adultsTextView = (TextView)findViewById(R.id.adultsTextView);
        seniorTextView = (TextView)findViewById(R.id.seniors);
        childrenTextView = (TextView)findViewById(R.id.childern);
        fromTextView = (TextView)findViewById(R.id.from);
        toTextView = (TextView)findViewById(R.id.to);
        date = (TextView)findViewById(R.id.date);
        time = (TextView)findViewById(R.id.time);

        //retrieve no of passengers
        sharedpreferences = getSharedPreferences("Pref", Context.MODE_PRIVATE);
        adultsTextView.setText(sharedpreferences.getString("Adults", ""));
        seniorTextView.setText(sharedpreferences.getString("Seniors", ""));
        childrenTextView.setText(sharedpreferences.getString("Children", ""));
        fromTextView.setText(sharedpreferences.getString("From", ""));
        toTextView.setText(sharedpreferences.getString("To", ""));


        //Get the bundle
        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        final String from = bundle.getString("Date");
        final String to = bundle.getString("Time");

        date.setText(from);
        time.setText(to);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(totalAmountActivity.this, PaymentActivity.class);
                totalAmountActivity.this.startActivity(intent);
            }
        });
    }

    public double calcTotal(double distance, int []passengers){
//        if(passengers[0] == adults){
//
//        }
        return 0;
    }
}
