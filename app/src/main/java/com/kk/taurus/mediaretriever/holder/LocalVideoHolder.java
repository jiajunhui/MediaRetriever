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

public class LocalVideoHolder extends ContentHolder<HolderData> {

    private RecyclerView mRecycler;
    private LocalVideoAdapter mAdapter;
    private List<VideoItem> mItems = new ArrayList<>();

    public LocalVideoHolder(Context context) {
        super(context);
    }

    public LocalVideoHolder(Context context, OnHolderListener onHolderListener) {
        super(context, onHolderListener);
    }

    @Override
    public void onCreate() {
        setContentView(R.layout.fragment_local_video);
        mRecycler = getViewById(R.id.recycler);

    }

    public void setVideoItems(List<VideoItem> items){
        mItems.clear();
        mItems.addAll(items);
//        mItems.add(items.get(0));
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onHolderCreated() {
        super.onHolderCreated();
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        mAdapter = new LocalVideoAdapter(mContext,mItems);
        mRecycler.setAdapter(mAdapter);
    }

}
