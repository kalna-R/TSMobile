package com.example.ts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.util.Log.println;

public class AddTravellersActivity extends AppCompatActivity {

    TextView fromTextView, toTextView, distanceTextView;
    TextView adultTextView, seniorTextView, childTextView;

    Button adultAdd, adultMinus, seniorAdd, seniorMinus, childAdd, childMinus,nextBtn;

    private static int adults = 0;
    private static int seniors = 0;
    private static int children = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travellers);

        fromTextView = (TextView)findViewById(R.id.from);
        toTextView = (TextView)findViewById(R.id.to);
        distanceTextView = (TextView)findViewById(R.id.distance);

        adultAdd = (Button)findViewById(R.id.increaseAdultBtn);
        seniorAdd = (Button)findViewById(R.id.increaseSeniorBtn);
        childAdd = (Button)findViewById(R.id.increaseChildBtn);

        adultMinus = (Button)findViewById(R.id.decreaseAdultBtn);
        seniorMinus = (Button)findViewById(R.id.decreaseSeniorBtn);
        childMinus = (Button)findViewById(R.id.decreaseChildBtn);

        adultTextView = (TextView)findViewById(R.id.noOfAdults);
        seniorTextView = (TextView)findViewById(R.id.noOfSeniors);
        childTextView = (TextView)findViewById(R.id.noOfChildren);

        nextBtn = (Button)findViewById(R.id.nextBtn);

        //Get the bundle
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        String from = bundle.getString("Origin");
        String to = bundle.getString("Destination");
        String dist = bundle.getString("Distance");

        fromTextView.setText(from);
        toTextView.setText(to);
        distanceTextView.setText("Distance " + dist);

        adultAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adults++;
                adultTextView.setText(String.valueOf(adults));
            }
        });

        adultMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adults > 0){
                    adults--;
                    adultTextView.setText(String.valueOf(adults));
                }
            }
        });

        seniorAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seniors++;
                seniorTextView.setText(String.valueOf(seniors));
            }
        });

        seniorMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seniors > 0){
                    seniors--;
                    seniorTextView.setText(String.valueOf(seniors));
                }
            }
        });

        childAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                children++;
                childTextView.setText(String.valueOf(children));
            }
        });

        childMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(children > 0 ){
                    children--;
                    childTextView.setText(String.valueOf(children));
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTravellersActivity.this, DateTimeActivity.class);
                AddTravellersActivity.this.startActivity(intent);

            }
        });

    }


}
