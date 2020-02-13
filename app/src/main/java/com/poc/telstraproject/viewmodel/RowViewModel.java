package com.poc.telstraproject.viewmodel;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.poc.telstraproject.R;
import com.poc.telstraproject.model.JsonData;
import com.poc.telstraproject.network.ApiClient;
import com.poc.telstraproject.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RowViewModel extends AndroidViewModel {

    private Context mContext;
    private MutableLiveData<String> showSnackBarMessage;
    private MutableLiveData<JsonData> jsonData;
    private ApiInterface apiInterface;

    public RowViewModel(@NonNull Application application) {
        super(application);
        mContext = application.getApplicationContext();
        showSnackBarMessage = new MutableLiveData<>();
    }


    public LiveData<JsonData> getData() {
        if (jsonData == null)
        {
            jsonData = new MutableLiveData<JsonData>();
            loadData();
        }
        return jsonData;
    }


    private void loadData() {
        setupApiClient();
        Call<JsonData> call = apiInterface.getData();

        call.enqueue(new Callback<JsonData>() {
            @Override
            public void onResponse(Call<JsonData> call, Response<JsonData> response) {

                jsonData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<JsonData> call, Throwable t) {
                showSnackBarMessage.setValue(mContext.getString(R.string.response_failure));
            }
        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    private void fetchData() {
        if (isNetworkConnected()) {

            showSnackBarMessage.setValue(mContext.getString(R.string.interrupted_connection));
        }
    }

    public MutableLiveData<String> getSnackBarMessage() {
        return showSnackBarMessage;
    }

    private void setupApiClient() {
        apiInterface = ApiClient.getRetrofit(mContext).create(ApiInterface.class);
    }
}
