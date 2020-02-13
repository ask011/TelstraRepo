package com.poc.telstraproject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.poc.telstraproject.model.JsonData;
import com.poc.telstraproject.network.ApiClient;
import com.poc.telstraproject.network.ApiInterface;
import com.poc.telstraproject.view.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TelstraTestCase {

    Context appContext;
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void useAppContext() {
        // Context of the app under test.
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    }

    @Test
    public void checkInternetConnection()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) activityRule.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        assertTrue(activeNetwork.isConnectedOrConnecting());
    }


    @Test
    public void getData()
    {
         ApiInterface apiInterface = ApiClient.getRetrofit(appContext).create(ApiInterface.class);
            Call<JsonData> call = apiInterface.getData();
            try {
                Response<JsonData> response = call.execute();
                assertTrue(response.isSuccessful());
            } catch (IOException e) {
                e.printStackTrace();
                assertTrue(false);
            }

    }

}
