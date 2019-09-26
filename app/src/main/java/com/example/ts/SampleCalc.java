//package com.example.ts;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.os.AsyncTask;
//import android.util.Log;
//
//public class SampleCalc extends AsyncTask<String, Void, String> {
//
//    ProgressDialog progressDialog;
//    Context context;
//    SampleCalc.Result result;
//
//    public SampleCalc(Context context){
//        this.context = context;
//        result = (SampleCalc.Result) context;
//    }
//
//    @Override
//    protected  void onPreExecute(){
//        super.onPreExecute();
//        progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage("Loading");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
//    }
//
//    @Override
//    protected String doInBackground(String... params) {
//        int distance = 15;
//
//        int result = distance * 10;
//
//        return "Price ==== " + result ;
//
//    }
//
//    interface Result{
////        public void setDouble(String min);
//    }
//}
