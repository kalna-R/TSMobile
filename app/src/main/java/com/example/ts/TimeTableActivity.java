package com.example.ts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class TimeTableActivity extends AppCompatActivity implements View.OnClickListener {

    EditText busRoute, from, to, time;
    Button addBus, btnTimePicker;
    private int mHour, mMinute;

    DatabaseReference databaseTicketing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        databaseTicketing = FirebaseDatabase.getInstance().getReference("bus");

        busRoute = (EditText)findViewById(R.id.busNo);
        from = (EditText)findViewById(R.id.from);
        to = (EditText)findViewById(R.id.to);
        time = (EditText)findViewById(R.id.timeTextView);

        addBus = (Button)findViewById(R.id.addBus);
        btnTimePicker = (Button)findViewById(R.id.btn_time);

        btnTimePicker.setOnClickListener(this);

        addBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBus();
            }
        });
    }


    private void addBus(){
        String busRouteNum = busRoute.getText().toString().trim();
        String origin = from.getText().toString().trim();
        String destination = to.getText().toString().trim();
        String busTime = time.getText().toString().trim();

        if(!TextUtils.isEmpty(busRouteNum) && !TextUtils.isEmpty(origin) && !TextUtils.isEmpty(destination)
                && !TextUtils.isEmpty(busTime)) {

            Bus bus = new Bus(busRouteNum, origin, destination, busTime);

            databaseTicketing.child(busRouteNum).setValue(bus);

            Toast.makeText(this, "Bus created successfully", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(TimeTableActivity.this, ViewTimeTableActivity.class);
            TimeTableActivity.this.startActivity(intent);

        }else{
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {

        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            time.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}
