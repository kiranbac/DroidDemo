package com.wpr.newsapplication.activities.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.wpr.newsapplication.R;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private RecyclerView factsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        if (isConnected(mContext)) {
            //Inform user about network connectivity
            Toast.makeText(mContext, R.string.network_connection_error, Toast.LENGTH_SHORT).show();
        }
    }


    public boolean isConnected(Context context) {
        //Checking for network connectivity
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(
                Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
