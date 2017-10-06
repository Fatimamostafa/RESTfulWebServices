package com.fatimamostafa.restfulwebservices.asynctaskloader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fatimamostafa.restfulwebservices.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AsyncTaskLoaderRequestActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    @BindView(R.id.tvSampleText)
    TextView tvSampleText;
    @BindView(R.id.btnRun)
    Button btnRun;
    @BindView(R.id.btnClear)
    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_loader_request);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnRun, R.id.btnClear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnRun:
                //getSupportLoaderManager().initLoader(0, null, this).forceLoad();
                getSupportLoaderManager().restartLoader(0, null, this).forceLoad();
                break;
            case R.id.btnClear:
                tvSampleText.setText("");
                break;
        }
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        tvSampleText.append("creating loader\n");
        return new MyTaskLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        tvSampleText.append("Loader finished, returned: " + data + "\n");
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    private static class MyTaskLoader extends AsyncTaskLoader<String> {

        public MyTaskLoader(Context context) {
            super(context);
        }

        @Override
        public String loadInBackground() {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "from the loader";
        }

        @Override
        public void deliverResult(String data) {
            data += ", delivered";
            super.deliverResult(data);
        }
    }
}
