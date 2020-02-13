package com.poc.telstraproject;

import com.poc.telstraproject.network.ApiClient;
import com.poc.telstraproject.network.ApiInterface;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnit4.class)
public class ViewModelUnitTest {


    @Mock
    ApiInterface apiInterface;

    @Mock
    ApiClient apiClient;

    @Rule
    /*public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule(MainActivity.class);*/

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
//        apiClient = ApiClient.getRetrofit().create(ApiInterface.class);
    }

    @Test
    public void getData()
    {

    }

}