package com.example.ts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class VerifyPasswordActivity extends AppCompatActivity {

    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_password);

        confirm = (Button)findViewById(R.id.confirmBtn);

//        Intent intent = new Intent(VerifyPasswordActivity.this, PaymentActivity.class);
//        VerifyPasswordActivity.this.startActivity(intent);
    }
}
