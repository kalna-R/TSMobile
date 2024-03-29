package com.example.ts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InvalidActivity extends AppCompatActivity {

    Button homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invalid);

        homeBtn = (Button)findViewById(R.id.button);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InvalidActivity.this, PurchaseTokenActivity.class);
                InvalidActivity.this.startActivity(intent);

            }
        });
    }
}
