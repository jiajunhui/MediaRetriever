package com.kk.taurus.mediaretriever.holder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jiajunhui.xapp.medialoader.bean.VideoItem;
import com.kk.taurus.mediaretriever.R;
import com.kk.taurus.mediaretriever.adapter.LocalVideoAdapter;
import com.kk.taurus.uiframe.i.HolderData;
import com.kk.taurus.uiframe.listener.OnHolderListener;
import com.kk.taurus.uiframe.v.ContentHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taurus on 2017/12/7.
 */

public class OnLineVideoHolder extends ContentHolder<HolderData> {

    private List<VideoItem> mItems = new ArrayList<>();
    private RecyclerView mRecycler;
    private LocalVideoAdapter mAdapter;

    private String[] mUrls = {
            "http://jiajunhui.cn/video/edwin_rolling_in_the_deep.flv",
            "http://jiajunhui.cn/video/allsharestar.mp4",
            "http://jiajunhui.cn/video/crystalliz.flv",
            "http://jiajunhui.cn/video/big_buck_bunny.mp4",
            "http://jiajunhui.cn/video/trailer.mp4"
    };

    public OnLineVideoHolder(Context context) {
        super(context);
    }

    public OnLineVideoHolder(Context context, OnHolderListener onHolderListener) {
        super(context, onHolderListener);
    }

    @Override
    public void onCreate() {
        setContentView(R.layout.fragment_local_video);
        mRecycler = getViewById(R.id.recycler);
    }

    @Override
    public void onHolderCreated() {
        super.onHolderCreated();
        VideoItem item;
        for(int i=0;i<5;i++){
            item = new VideoItem();
            item.setPath(mUrls[i]);
            item.setDisplayName(mUrls[i]);
            mItems.add(item);
        }
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        mAdapter = new LocalVideoAdapter(mContext,mItems);
        mRecycler.setAdapter(mAdapter);
    }
}
