package com.fatimamostafa.restfulwebservices.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fatimamostafa.restfulwebservices.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AsyncTaskRequestActivity extends AppCompatActivity {

    @BindView(R.id.btnRun)
    Button btnRun;
    @BindView(R.id.btnClear)
    Button btnClear;
    @BindView(R.id.tvSampleText)
    TextView tvSampleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_request);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnRun, R.id.btnClear})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btnRun:
                //tvSampleText.append("Button clicked\n");

                MyAsyncTask task = new MyAsyncTask();
                task.execute("String 1", "String 2", "String 3", "String 4");
                break;

            case R.id.btnClear:
                tvSampleText.setText("");
                break;
        }
    }

    private class MyAsyncTask extends AsyncTask<String, String, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            for(String string: strings) {
                publishProgress(string);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            tvSampleText.append(values[0] + "\n");
        }
    }
}
