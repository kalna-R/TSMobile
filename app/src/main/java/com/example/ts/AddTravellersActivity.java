package com.example.ts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddTravellersActivity extends AppCompatActivity {

    TextView fromTextView, toTextView, distanceTextView;
    TextView adultTextView, seniorTextView, childTextView;

    Button adultAdd, adultMinus, seniorAdd, seniorMinus, childAdd, childMinus,nextBtn;

    private static int adults = 0;
    private static int seniors = 0;
    private static int children = 0;

    SharedPreferences sharedPref;

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

        nextBtn = (Button)findViewById(R.id.totalNextButton);

        //Get the bundle
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        final String from = bundle.getString("Origin");
        final String to = bundle.getString("Destination");
        final String dist = bundle.getString("Distance");

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

        // Create object of SharedPreferences.
        sharedPref = getApplicationContext().getSharedPreferences("Pref", Context.MODE_PRIVATE);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get Editor
                SharedPreferences.Editor editor = sharedPref.edit();
                //put value
                editor.putString("Adults", String.valueOf(adults));
                editor.putString("Seniors", String.valueOf(seniors));
                editor.putString("Children", String.valueOf(children));
                editor.putString("From", from);
                editor.putString("To", to);
                editor.putString("Distance", dist);
                //commit edits
                editor.commit();

                Intent intent = new Intent(AddTravellersActivity.this, DateTimeActivity.class);
                AddTravellersActivity.this.startActivity(intent);
            }
        });

    }


}
