package com.appodeal.support.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appodeal.ads.Appodeal;

public class MainActivity extends AppCompatActivity implements com.appodeal.support.test.View {

    private String appKey = "bb9bec170dab2e38edcc82f568cb3a6c9a924e8973d7a826";
    private Button mButtonDisable, mButtonNative;
    private TextView mTextViewCount;
    private Presenter mPresenter;
    private boolean isCheckDisable = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            isCheckDisable = savedInstanceState.getBoolean("check");
        }
        mButtonDisable = findViewById(R.id.disable_button);
        mTextViewCount = findViewById(R.id.count_timer);
        mButtonNative = findViewById(R.id.native_id);

        Appodeal.disableLocationPermissionCheck();
        Appodeal.initialize(this, appKey, Appodeal.INTERSTITIAL | Appodeal.BANNER |
                Appodeal.NATIVE);

        Appodeal.setTesting(true);

        mPresenter = new Presenter(this, this, mTextViewCount);

        mButtonDisable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.clickDisable();
            }
        });

        mButtonNative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.clickNative();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!isCheckDisable) {
            mPresenter.onStartTimer();
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("check", isCheckDisable);
    }

    @Override
    public void onDisableClick() {
        isCheckDisable = true;
        mPresenter.onCancelTimer();
    }

    @Override
    public void onClickNative() {
        mPresenter.onCancelTimer();

        Intent intent = new Intent(MainActivity.this, NativeActivity.class);
        startActivity(intent);
    }


}
