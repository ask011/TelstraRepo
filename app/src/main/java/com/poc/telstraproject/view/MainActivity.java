package com.poc.telstraproject.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.poc.telstraproject.R;
import com.poc.telstraproject.model.JsonData;
import com.poc.telstraproject.network.NetworkUtils;
import com.poc.telstraproject.viewmodel.RowViewModel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RowViewModel model;
    private ActionBar actionBar;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        swipeRefreshLayout = findViewById(R.id.simpleSwipeRefreshLayout);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        model = ViewModelProviders.of(this).get(RowViewModel.class);
        fetchDataIfInternetAvailable();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                fetchDataIfInternetAvailable();
            }
        });
    }

    private void fetchDataIfInternetAvailable()
    {
        if(NetworkUtils.isNetworkAvailable(this))
        {
            getListInfo();
        }
        else
        {
            showAlert();
        }
    }

    private void getListInfo()
    {
        model.getData().observe(this, new Observer<JsonData>() {
            @Override
            public void onChanged(JsonData response) {
                if(response != null)
                {
                    if (actionBar != null)
                    {
                        String headerTitle = response.getTitle();
                        if (headerTitle != null && !headerTitle.isEmpty()) {
                            actionBar.setTitle(response.getTitle());
                        }
                    }

                    if (adapter == null)
                    {
                        adapter = new ListAdapter(response.getRowList());
                        recyclerView.setAdapter(adapter);
                    } else
                    {
                        adapter.setData(response.getRowList());
                        adapter.notifyDataSetChanged();
                    }

                    if (swipeRefreshLayout != null)
                    {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
            }
        });
    }

    private void showAlert()
    {
        builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.alert_msg)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle(R.string.alert_title);
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_refresh:
                fetchDataIfInternetAvailable();
        }
        return super.onOptionsItemSelected(item);
    }
}
