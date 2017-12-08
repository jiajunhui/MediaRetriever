package com.kk.taurus.mediaretriever.ui;

import com.kk.taurus.mediaretriever.holder.OnLineVideoHolder;
import com.kk.taurus.uiframe.f.StateFragment;
import com.kk.taurus.uiframe.i.HolderData;
import com.kk.taurus.uiframe.v.ContentHolder;

/**
 * Created by Taurus on 2017/12/7.
 */

public class OnLineVideoFragment extends StateFragment<HolderData,OnLineVideoHolder> {

    @Override
    protected void onLoadState() {
        super.onLoadState();
    }

    @Override
    public ContentHolder onBindContentHolder() {
        return new OnLineVideoHolder(mContext,this);
    }
}
