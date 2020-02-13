package com.poc.telstraproject.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.poc.telstraproject.model.JsonData;
import com.poc.telstraproject.repository.DataRepository;

public class RowViewModel extends AndroidViewModel {

    private LiveData<JsonData> jsonData;

    public RowViewModel(@NonNull Application application) {
        super(application);
        jsonData = DataRepository.getInstance(application.getApplicationContext()).getData();
    }


    public LiveData<JsonData> getData() {
        return jsonData;
    }
}
