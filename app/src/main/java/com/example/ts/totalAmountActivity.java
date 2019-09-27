package com.example.ts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class totalAmountActivity extends AppCompatActivity {

    Button nextBtn;
    TextView adultsTextView, seniorTextView, childrenTextView, fromTextView, toTextView, date, time, totalTextView, balanceTextView;

    SharedPreferences sharedpreferences;
    DatabaseReference databaseTicketing;

    ArrayList list = new ArrayList();

    String noOfAdults, noOfSeniors, noOfChildren, distance, origin, destination;
    final double fareChild = 6, fareAdult = 10, fareSenior = 8;
    double totalA, totalS, totalC, total = 0;
//    int ticketId = 3;

    String sDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_amount);

        databaseTicketing = FirebaseDatabase.getInstance().getReference("ticket");

        nextBtn = (Button)findViewById(R.id.totalNextButton);
        adultsTextView = (TextView)findViewById(R.id.adultsTextView);
        seniorTextView = (TextView)findViewById(R.id.seniors);
        childrenTextView = (TextView)findViewById(R.id.childern);
        fromTextView = (TextView)findViewById(R.id.from);
        toTextView = (TextView)findViewById(R.id.to);
        date = (TextView)findViewById(R.id.textViewDate);
        time = (TextView)findViewById(R.id.time);
        totalTextView = (TextView)findViewById(R.id.total);
        balanceTextView = (TextView)findViewById(R.id.balance);

        //retrieve no of passengers
        sharedpreferences = getSharedPreferences("Pref", Context.MODE_PRIVATE);

        noOfAdults = sharedpreferences.getString("Adults", "");
        noOfSeniors = sharedpreferences.getString("Children", "");
        noOfChildren = sharedpreferences.getString("Seniors", "");
        distance = sharedpreferences.getString("Distance", "");

        origin = sharedpreferences.getString("From", "");
        destination = sharedpreferences.getString("To", "");

        adultsTextView.setText(noOfAdults);
        seniorTextView.setText(noOfSeniors);
        childrenTextView.setText(noOfChildren);

        fromTextView.setText(origin);
        toTextView.setText(destination);

        //Get the bundle
        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        sDate = bundle.getString("Date");
        final String sTime = bundle.getString("Time");

        date.setText(sDate);
        time.setText(sTime);

        Double result = calcTotal();
        totalTextView.setText(String.valueOf(result));
        double balance = 10000 - result;
        balanceTextView.setText(String.valueOf(balance));


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTicket();
//                Intent intent = new Intent(totalAmountActivity.this, PaymentActivity.class);
//                totalAmountActivity.this.startActivity(intent);
            }
        });
    }

    //method to calculate total amount
    public double calcTotal(){

        Double dist = Double.parseDouble(distance);
        Double adults = Double.parseDouble(noOfAdults);
        Double children = Double.parseDouble(noOfChildren);
        Double seniors = Double.parseDouble(noOfSeniors);

        if(adults > 0 || children > 0 || seniors > 0 ){
            totalA = fareAdult * dist * adults;
            totalS = fareSenior * dist * seniors;
            totalC = fareChild * dist * children;
        }

        total = totalA + totalC + totalS;

        return (total);
    }

    private void createTicket(){

            String id = databaseTicketing.push().getKey();
            Ticket ticket = new Ticket(id, origin, destination, Double.valueOf(distance),
                    Integer.valueOf(noOfSeniors), Integer.valueOf(noOfChildren), Integer.valueOf(noOfAdults),
                    total, sDate, "kalna");

            databaseTicketing.child(String.valueOf(id)).setValue(ticket);

            Toast.makeText(this, "Ticket created successfully", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(totalAmountActivity.this, QRScannerActivity.class);
            totalAmountActivity.this.startActivity(intent);

//        }else{
//            Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show();
//        }

    }
}
