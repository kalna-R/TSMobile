package com.example.ts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextPhone, editTextAddress, editTextEmail;
    Spinner spinnerGender, spinnerPassengerType;
    Button buttonSignUp;

    DatabaseReference databaseTicketing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        database = FirebaseDatabase.getInstance();
        databaseTicketing = FirebaseDatabase.getInstance().getReference("passenger");

        editTextName = (EditText)findViewById(R.id.name);
        editTextPhone = (EditText)findViewById(R.id.phone);
        editTextAddress = (EditText)findViewById(R.id.address);
        editTextEmail = (EditText)findViewById(R.id.email);

        spinnerGender = (Spinner)findViewById(R.id.gender);
        spinnerPassengerType = (Spinner)findViewById(R.id.passengerType);

        buttonSignUp = (Button)findViewById(R.id.createAccountButton);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPassenger();
            }
        });
    }

    private  void addPassenger(){
        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();

        String gender = spinnerGender.getSelectedItem().toString();
        String passengerType = spinnerPassengerType.getSelectedItem().toString();

//        if(){
//            Toast.makeText(this, "Username exists", Toast.LENGTH_LONG).show();
//        }

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(address) && !TextUtils.isEmpty(phone)
                && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(gender) && !TextUtils.isEmpty(passengerType)) {

            Passenger passenger = new Passenger(name, address, email, phone, gender, passengerType);

            databaseTicketing.child(email).setValue(passenger);

            Toast.makeText(this, "Account created successfully", Toast.LENGTH_LONG).show();

//            startActivity();

        }else{
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_LONG).show();
        }

    }
}
