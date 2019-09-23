package com.example.ts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PurchaseTokenActivity extends AppCompatActivity {

    Button buyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_token);

        buyBtn = (Button)findViewById(R.id.buyTokenBtn);

        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PurchaseTokenActivity.this, TokenTypeActivity.class);
                PurchaseTokenActivity.this.startActivity(intent);

            }
        });
    }
}
