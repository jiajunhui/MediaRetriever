package com.kk.taurus.mediaretriever.holder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jiajunhui.xapp.medialoader.bean.AudioItem;
import com.kk.taurus.mediaretriever.R;
import com.kk.taurus.mediaretriever.adapter.AudioAdapter;
import com.kk.taurus.uiframe.i.HolderData;
import com.kk.taurus.uiframe.listener.OnHolderListener;
import com.kk.taurus.uiframe.v.ContentHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taurus on 2017/12/7.
 */

public class AudioHolder extends ContentHolder<HolderData> {

    private RecyclerView mRecycler;
    private AudioAdapter mAdapter;
    private List<AudioItem> mItems = new ArrayList<>();

    public AudioHolder(Context context) {
        super(context);
    }

    public AudioHolder(Context context, OnHolderListener onHolderListener) {
        super(context, onHolderListener);
    }

    @Override
    public void onCreate() {
        setContentView(R.layout.fragment_audio);
        mRecycler = getViewById(R.id.recycler);
    }

    @Override
    public void onHolderCreated() {
        super.onHolderCreated();
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        mAdapter = new AudioAdapter(mContext,mItems);
        mRecycler.setAdapter(mAdapter);
    }

    public void setAudioList(List<AudioItem> items){
        mItems.clear();
        mItems.addAll(items);
        mAdapter.notifyDataSetChanged();
    }
}
