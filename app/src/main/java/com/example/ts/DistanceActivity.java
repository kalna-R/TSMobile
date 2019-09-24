package com.example.ts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class DistanceActivity extends AppCompatActivity implements AppAsyncTask.Result {

    EditText editTextFrom, editTextTo;
    Button buttonNext;
    TextView textViewResult;
    String API_KEY = "AIzaSyD9A8WeC2B_PYaXfBIN7cgS5EtwdiPtCVk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);

        editTextFrom = (EditText)findViewById(R.id.editText_from);
        editTextTo = (EditText)findViewById(R.id.editText_to);

        buttonNext = (Button)findViewById(R.id.button_get);

        textViewResult = (TextView)findViewById(R.id.textView_info);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String from = editTextFrom.getText().toString();
                String to = editTextTo.getText().toString();
                String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + from + "&destinations=" + to + "&mode=driving&language=fr-FR&avoid=tolls&key=" + API_KEY;
                new AppAsyncTask(DistanceActivity.this).execute(url);

                Intent intent = new Intent(DistanceActivity.this, AddTravellersActivity.class);
                DistanceActivity.this.startActivity(intent);

//                Bundle bundle = new Bundle();
//                bundle.putString("Origin", from);
//                bundle.putString("Destination", to);
//                intent.putExtras(bundle);
//                DistanceActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    public void setDouble(String result){
        String res[]=result.split(",");
        Double min=Double.parseDouble(res[0])/60;
        int dist=Integer.parseInt(res[1])/1000;
//        tv_result1.setText("Duration= " + (int) (min / 60) + " hr " + (int) (min % 60) + " mins");
        textViewResult.setText("Distance= " + dist + " kilometers");
    }
}
