package com.example.ts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TicketTypeActivity extends AppCompatActivity {

    Button singleTicketBTn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_type);

        singleTicketBTn = (Button)findViewById(R.id.singleTcktBtn);

        singleTicketBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TicketTypeActivity.this, DistanceActivity.class);
                TicketTypeActivity.this.startActivity(intent);
            }
        });
    }
}
