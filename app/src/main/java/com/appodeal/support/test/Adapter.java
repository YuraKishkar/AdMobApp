package com.appodeal.support.test;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.view.View;
import android.widget.ProgressBar;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.NativeAd;
import com.appodeal.ads.native_ad.views.NativeAdViewAppWall;
import com.appodeal.ads.native_ad.views.NativeAdViewContentStream;

import java.util.List;

/**
 * Created by liban on 26.09.2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<NativeAd> list;


    public Adapter( List<NativeAd> nativeAd) {
        list = nativeAd;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.list_native, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.mNativeAdViewContentStream.setNativeAd(list.get(i));
        if (getItemCount()!= 0) {
            viewHolder.mProgressBar.setVisibility(View.GONE);
        }


    }

    public void addList(List<NativeAd> newNativeAd) {
        while (list.size() < 10) {
            list.addAll(newNativeAd);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else{
            return 0;
    }
}

public class ViewHolder extends RecyclerView.ViewHolder {
    private NativeAdViewAppWall mNativeAdViewContentStream;
    private ProgressBar mProgressBar;

    public ViewHolder(android.view.View itemView) {
        super(itemView);
        mNativeAdViewContentStream = itemView.findViewById(R.id.native_ad_view_app_wall);
        mProgressBar = itemView.findViewById(R.id.progress_id);
    }
}
}
