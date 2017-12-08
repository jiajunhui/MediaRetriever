package com.kk.taurus.mediaretriever.ui;

import android.os.Bundle;

import com.jiajunhui.xapp.medialoader.bean.VideoItem;
import com.jiajunhui.xapp.medialoader.callback.OnVideoLoaderCallBack;
import com.jiajunhui.xapp.medialoader.loader.MediaLoader;
import com.kk.taurus.mediaretriever.holder.LocalVideoHolder;
import com.kk.taurus.uiframe.d.BaseState;
import com.kk.taurus.uiframe.f.StateFragment;
import com.kk.taurus.uiframe.i.HolderData;
import com.kk.taurus.uiframe.v.ContentHolder;

import java.util.List;

/**
 * Created by Taurus on 2017/12/7.
 */

public class LocalVideoFragment extends StateFragment<HolderData,LocalVideoHolder> {
    @Override
    public ContentHolder onBindContentHolder() {
        return new LocalVideoHolder(mContext,this);
    }

    @Override
    protected void onLoadState() {
        setPageState(BaseState.LOADING);
        MediaLoader.loadVideos(getActivity(), new OnVideoLoaderCallBack() {
            @Override
            public void onResultList(List<VideoItem> items) {
                getUserContentHolder().setVideoItems(items);
                setPageState(BaseState.SUCCESS);
            }
        });
    }

    @Override
    protected void onInit(Bundle savedInstanceState) {
        super.onInit(savedInstanceState);

    }
}
