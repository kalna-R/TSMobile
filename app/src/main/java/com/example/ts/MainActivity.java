package com.example.ts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextPhone, editTextAddress, editTextEmail, inputpassword;
    Spinner spinnerGender, spinnerPassengerType;
    Button buttonSignUp, loginbtn;


    DatabaseReference databaseTicketing;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

//        database = FirebaseDatabase.getInstance();
        databaseTicketing = FirebaseDatabase.getInstance().getReference("passenger");

        editTextName = (EditText)findViewById(R.id.name);
        editTextPhone = (EditText)findViewById(R.id.phone);
        editTextAddress = (EditText)findViewById(R.id.address);
        editTextEmail = (EditText)findViewById(R.id.email);
        inputpassword = (EditText)findViewById(R.id.password);

        spinnerGender = (Spinner)findViewById(R.id.gender);
        spinnerPassengerType = (Spinner)findViewById(R.id.passengerType);

        buttonSignUp = (Button)findViewById(R.id.createAccountButton);

        loginbtn = (Button)findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editTextEmail.getText().toString().trim();
                String password = inputpassword.getText().toString().trim();
                String name = editTextName.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                addPassenger();

//                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(MainActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
//                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                    finish();
                                }
                            }
                        });
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

            int index1=email.indexOf("@");

            String finalemail = (email.substring(0,index1));

            databaseTicketing.child(finalemail).setValue(passenger);



            Toast.makeText(this, "Account created successfully", Toast.LENGTH_LONG).show();

//            startActivity();

        }else{
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_LONG).show();
        }

    }
}
