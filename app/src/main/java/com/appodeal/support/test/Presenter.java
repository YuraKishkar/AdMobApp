package com.appodeal.support.test;

import android.content.Context;
import android.widget.TextView;

import java.security.PublicKey;

/**
 * Created by liban on 25.09.2018.
 */

public class Presenter {
    private View mView;
    private Model mModel;

    public Presenter(View mView, Context context, TextView textView) {
        this.mView = mView;
        mModel = new Model(context,textView);
    }

    public void clickDisable(){
     mView.onDisableClick();
    }

    public void clickNative(){
        mView.onClickNative();
    }

    public void onCancelTimer(){
        mModel.cancelTimer();
    }

    public void onStartTimer(){
        mModel.startTimer();
    }
}
