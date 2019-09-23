package com.example.ts;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AppAsyncTask extends AsyncTask<String, Void, String> {

    ProgressDialog progressDialog;
    Context context;
    Result result;

    public AppAsyncTask(Context context){
        this.context = context;
        result = (Result) context;
    }

    @Override
    protected  void onPreExecute(){
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if(statusCode == HttpURLConnection.HTTP_OK)
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while(line != null)
                {
                    sb.append(line);
                    line = br.readLine();
                }
                String json = sb.toString();
                Log.d("JSON",json);
                JSONObject root = new JSONObject(json);
                JSONArray array_rows = root.getJSONArray("rows");
                Log.d("JSON","array_rows:"+array_rows);
                JSONObject object_rows = array_rows.getJSONObject(0);
                Log.d("JSON","object_rows:"+object_rows);
                JSONArray array_elements = object_rows.getJSONArray("elements");
                Log.d("JSON","array_elements:"+array_elements);
                JSONObject  object_elements = array_elements.getJSONObject(0);
                Log.d("JSON","object_elements:"+object_elements);
                JSONObject object_duration = object_elements.getJSONObject("duration");
                JSONObject object_distance = object_elements.getJSONObject("distance");

                Log.d("JSON","object_duration:"+object_duration);
                return object_duration.getString("value")+","+object_distance.getString("value");

            }
        } catch (MalformedURLException e) {
            Log.d("error", "error1");
        } catch (IOException e) {
            Log.d("error", "error2");
        } catch (JSONException e) {
            Log.d("error","error3");
        }


        return null;

    }

    
    interface Result{
        public void setDouble(String min);
    }

}
