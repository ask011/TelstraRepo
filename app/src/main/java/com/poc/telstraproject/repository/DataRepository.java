package com.poc.telstraproject.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.poc.telstraproject.R;
import com.poc.telstraproject.model.JsonData;
import com.poc.telstraproject.network.ApiClient;
import com.poc.telstraproject.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {

    private ApiInterface apiInterface;
    private static DataRepository dataRepository;

    private DataRepository(Context context){
        apiInterface = ApiClient.getRetrofit(context).create(ApiInterface.class);
    }

    public LiveData<JsonData> getData() {
        final MutableLiveData<JsonData> jsonData = new MutableLiveData<>();
        Call<JsonData> call = apiInterface.getData();
        call.enqueue(new Callback<JsonData>() {
            @Override
            public void onResponse(Call<JsonData> call, Response<JsonData> response) {
                jsonData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<JsonData> call, Throwable t) {
                jsonData.setValue(null);
            }
        });
        return jsonData;
    }

    public synchronized static DataRepository getInstance(Context context) {
        if (dataRepository == null)
            dataRepository = new DataRepository(context);
        return dataRepository;
    }

}
