package com.appodeal.support.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.view.View;
import android.widget.ProgressBar;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.NativeAd;
import com.appodeal.ads.NativeCallbacks;

import io.presage.finder.model.App;

public class NativeActivity extends AppCompatActivity {
    private Adapter adapter;
    private RecyclerView mRecyclerView;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);
        mRecyclerView = findViewById(R.id.recycler_id);
        Appodeal.cache(NativeActivity.this,Appodeal.NATIVE);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        nativeCallBacks();
        adapter = new Adapter( Appodeal.getNativeAds(5));
        mRecyclerView.setAdapter(adapter);
        mProgressBar = findViewById(R.id.progress_id);
    }

    private void nativeCallBacks(){
        Appodeal.setNativeCallbacks(new NativeCallbacks() {
            @Override
            public void onNativeLoaded() {
                adapter.addList(Appodeal.getNativeAds(5));

            }

            @Override
            public void onNativeFailedToLoad() {

            }

            @Override
            public void onNativeShown(NativeAd nativeAd) {


            }

            @Override
            public void onNativeClicked(NativeAd nativeAd) {

            }
        });
    }

}
