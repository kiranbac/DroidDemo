package com.wpr.newsapplication.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        progressDialog = new ProgressDialog(mContext);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        getFactsData();
    }

    private void getFactsData() {
        if (isInternetConnected(mContext)) {
            showDownloadProgress(progressDialog);
            Call<Facts> call = apiInterface.getFactsList();
            call.enqueue(new Callback<Facts>() {
                @Override
                @NonNull
                public void onResponse(@NonNull Call<Facts> call,@NonNull Response<Facts> response) {
                    Facts responseData = response.body();
                    if (response.isSuccessful()) {

                        factsList =  findViewById(R.id.facts_list);
                        factsList.setLayoutManager(new LinearLayoutManager(mContext));
                        DividerItemDecoration divider = new DividerItemDecoration(factsList.getContext(),
                                new LinearLayoutManager(mContext).getOrientation());
                        factsList.addItemDecoration(divider);
                        factsList.setItemAnimator(new DefaultItemAnimator());
                        FactsListAdapter adapter = new FactsListAdapter(responseData, mContext);
                        factsList.setAdapter(adapter);
                        ActionBar actionBar = getSupportActionBar();
                        if (actionBar != null) {
                            actionBar.setTitle(responseData.getTitle()+R.string.empty_string);
                        }
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Facts>  call, Throwable t) {
                    Toast.makeText(mContext, R.string.network_connection_error, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            //Inform user about network connectivity
            Toast.makeText(mContext, R.string.network_connection_error, Toast.LENGTH_SHORT).show();
        }
    }


    public void showDownloadProgress(ProgressDialog progressDialog) {
        progressDialog = progressDialog;
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading data ...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                getFactsData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean isInternetConnected(Context context) {
        //Checking network connectivity
        ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

}
