package com.appodeal.support.test;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.BannerCallbacks;
import com.appodeal.ads.InterstitialCallbacks;

/**
 * Created by liban on 25.09.2018.
 */

public class Model {

    private Context mContext;
    private TextView mTextViewCount;
    private CountDownTimer mCountDownTimer;

    public Model(Context mContext, TextView mTextViewCount) {
        this.mContext = mContext;
        this.mTextViewCount = mTextViewCount;
        appCallback();
        Timer();
        mCountDownTimer.start();
    }

    private void Timer() {
        mCountDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                mTextViewCount.setText(String.valueOf(l / 1000));

            }

            @Override
            public void onFinish() {
                showInterstitial();
            }
        };
    }

    public void startTimer() {
        mCountDownTimer.start();
    }

    public void cancelTimer() {
        mCountDownTimer.cancel();
    }

    private void showInterstitial() {
        Appodeal.show((Activity) mContext, Appodeal.INTERSTITIAL);
    }

    private void appCallback() {
        Appodeal.setBannerCallbacks(new BannerCallbacks() {
            @Override
            public void onBannerLoaded(int i, boolean b) {
                Appodeal.show((Activity) mContext, Appodeal.BANNER_TOP);
            }

            @Override
            public void onBannerFailedToLoad() {
                Toast.makeText(mContext, "ErrorBanner", Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onBannerShown() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Appodeal.hide((Activity) mContext, Appodeal.BANNER);
                    }
                }, 5000);
            }

            @Override
            public void onBannerClicked() {

            }
        });


        Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
            @Override
            public void onInterstitialLoaded(boolean b) {

            }

            @Override
            public void onInterstitialFailedToLoad() {

            }

            @Override
            public void onInterstitialShown() {
                cancelTimer();
            }

            @Override
            public void onInterstitialClicked() {

            }

            @Override
            public void onInterstitialClosed() {
               startTimer();
            }
        });
    }
}
