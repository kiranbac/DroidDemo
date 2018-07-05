package com.wpr.newsapplication.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class InternetConnected extends AsyncTask<Void, Void, Boolean> {
    private Context mContext;

    public InternetConnected(Context context) {
        mContext = context;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            //Check for connectivity first
            ConnectivityManager conManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                URL url = new URL("https://www.wipro.com/");
                HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
                httpConnection.setConnectTimeout(20000);
                httpConnection.connect();
                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
