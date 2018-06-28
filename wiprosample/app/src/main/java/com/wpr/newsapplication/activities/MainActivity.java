package com.wpr.newsapplication.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.wpr.newsapplication.R;
import com.wpr.newsapplication.adapter.FactsListAdapter;
import com.wpr.newsapplication.models.Facts;
import com.wpr.newsapplication.network.APIClient;
import com.wpr.newsapplication.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private RecyclerView factsList;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        if (isConnected(mContext)) {
            //Inform user about network connectivity
            Toast.makeText(mContext, R.string.network_connection_error, Toast.LENGTH_SHORT).show();
        }
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        getFactsData();
    }

    private void getFactsData() {
        Call<Facts> call = apiInterface.getFactsList();
        call.enqueue(new Callback<Facts>() {
            @Override
            public void onResponse(Call<Facts> call, Response<Facts> response) {
                Facts responseData = response.body();
                if (response.isSuccessful()) {

                    factsList = (RecyclerView) findViewById(R.id.facts_list);
                    factsList.setLayoutManager(new LinearLayoutManager(mContext));
                    factsList.setItemAnimator(new DefaultItemAnimator());
                    FactsListAdapter adapter = new FactsListAdapter(responseData, mContext);
                    factsList.setAdapter(adapter);
                    ActionBar actionBar = getSupportActionBar();
                    if (actionBar != null) {
                        actionBar.setTitle(responseData.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<Facts> call, Throwable t) {

            }
        });

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
