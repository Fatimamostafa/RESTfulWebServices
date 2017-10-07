package com.fatimamostafa.restfulwebservices;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fatimamostafa.restfulwebservices.IntentService.IntentServiceActivity;
import com.fatimamostafa.restfulwebservices.asynctask.AsyncTaskRequestActivity;
import com.fatimamostafa.restfulwebservices.asynctaskloader.AsyncTaskLoaderRequestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements OnClickCallback{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    HomeAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        String[] titles = {
                "AsyncTask Request",
                "AsyncTaskLoader Request",
                "Intent Service Request",
                "Nadia's Service"
        };

        mLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new HomeAdapter(titles,this,this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClickCallback(int position) {
        switch (position) {
            case 0:
                Intent aIntent = new Intent(this, AsyncTaskRequestActivity.class);
                startActivity(aIntent);
                break;
            case 1:
                Intent bIntent = new Intent(this, AsyncTaskLoaderRequestActivity.class);
                startActivity(bIntent);
                break;
            case 2:
                Intent cIntent = new Intent(this, IntentServiceActivity.class);
                startActivity(cIntent);
                break;
            case 3:
                Intent dIntent = new Intent(this, MainActivity.class);
                startActivity(dIntent);
                break;
        }
    }
}

