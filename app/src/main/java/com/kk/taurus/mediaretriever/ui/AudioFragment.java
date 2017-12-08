package com.kk.taurus.mediaretriever.ui;

import com.jiajunhui.xapp.medialoader.bean.AudioItem;
import com.jiajunhui.xapp.medialoader.callback.OnAudioLoaderCallBack;
import com.jiajunhui.xapp.medialoader.loader.MediaLoader;
import com.kk.taurus.mediaretriever.holder.AudioHolder;
import com.kk.taurus.uiframe.d.BaseState;
import com.kk.taurus.uiframe.f.StateFragment;
import com.kk.taurus.uiframe.i.HolderData;
import com.kk.taurus.uiframe.v.ContentHolder;

import java.util.List;

/**
 * Created by Taurus on 2017/12/7.
 */

public class AudioFragment extends StateFragment<HolderData,AudioHolder> {
    @Override
    public ContentHolder onBindContentHolder() {
        return new AudioHolder(mContext,this);
    }

    @Override
    protected void onLoadState() {
        setPageState(BaseState.LOADING);
        MediaLoader.loadAudios(getActivity(), new OnAudioLoaderCallBack() {
            @Override
            public void onResultList(List<AudioItem> items) {
                getUserContentHolder().setAudioList(items);
                setPageState(BaseState.SUCCESS);
            }
        });
    }
}
