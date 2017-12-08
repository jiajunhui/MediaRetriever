package com.kk.taurus.mediadataretriever.callback;

import android.graphics.Bitmap;

import com.kk.taurus.mediadataretriever.entity.MetadataKey;
import com.kk.taurus.mediadataretriever.inter.OnMediaRetrieverLoadListener;

import java.util.Map;

/**
 * Created by Taurus on 2017/12/7.
 */

public abstract class OnMediaRetrieverLoadCallback implements OnMediaRetrieverLoadListener {
    @Override
    public void onLoadStart() {

    }

    @Override
    public void onFrameGet(Bitmap bitmap) {

    }

    @Override
    public void onMetaDataGet(Map<MetadataKey, String> metaData) {

    }

    @Override
    public void onLoadFailure(int code, String message) {

    }
}
