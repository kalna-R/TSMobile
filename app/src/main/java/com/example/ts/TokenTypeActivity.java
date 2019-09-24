package com.example.ts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TokenTypeActivity extends AppCompatActivity {


    Button local, foreigner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_type);

        local = (Button)findViewById(R.id.PermanentTokenBtn);
        foreigner = (Button)findViewById(R.id.TemporaryTokenBtn);

        foreigner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TokenTypeActivity.this, DistanceActivity.class);
                TokenTypeActivity.this.startActivity(intent);
            }
        });

        local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TokenTypeActivity.this, TicketTypeActivity.class);
                TokenTypeActivity.this.startActivity(intent);
            }
        });
    }
}
