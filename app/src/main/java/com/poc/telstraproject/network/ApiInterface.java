package com.poc.telstraproject.network;

import com.poc.telstraproject.model.JsonData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("facts.json")
    Call<JsonData> getData();

}
