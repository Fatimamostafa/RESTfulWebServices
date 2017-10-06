package com.fatimamostafa.restfulwebservices.IntentService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fatimamostafa.restfulwebservices.R;
import com.fatimamostafa.restfulwebservices.services.MyService;
import com.fatimamostafa.restfulwebservices.utils.NetworkHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntentServiceActivity extends AppCompatActivity {

    private static final String JSON_URL = "http://560057.youcanlearnit.net/services/json/itemsfeed.php";

    @BindView(R.id.tvSampleText)
    TextView tvSampleText;

    @BindView(R.id.btnRun)
    Button btnRun;

    @BindView(R.id.btnClear)
    Button btnClear;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra(MyService.MY_SERVICE_PAYLOAD);
            tvSampleText.append(message + "\n");
        }
    };

    private boolean networkOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        ButterKnife.bind(this);

        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mBroadcastReceiver, new IntentFilter(MyService.MY_SERVICE_MESSAGE));

        networkOk = NetworkHelper.hasNetworkAccess(this);
        tvSampleText.append("Network ok: " + networkOk );
    }

    @OnClick({R.id.btnRun, R.id.btnClear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnRun:
                Intent intent = new Intent(this, MyService.class);
                intent.setData(Uri.parse(JSON_URL));
                startService(intent);
                startService(intent);
                startService(intent);
                break;
            case R.id.btnClear:
                tvSampleText.setText("");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext())
                .unregisterReceiver(mBroadcastReceiver);
    }
}
