package com.wpr.newsapplication.network;

import com.wpr.newsapplication.models.Facts;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Kiran on 1/13/2018.
 */

public interface ApiInterface {

    @GET("facts.json")
    Call<Facts> getFactsList();


}
