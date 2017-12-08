package com.kk.taurus.mediadataretriever.inter;

import android.graphics.Bitmap;

import com.kk.taurus.mediadataretriever.entity.MetadataKey;

import java.util.Map;

/**
 * Created by Taurus on 2017/12/6.
 */

public interface OnMediaRetrieverLoadListener {

    int CODE_FAILURE_FRAME_GET = 1;
    int CODE_FAILURE_META_DATA_GET = 2;

    void onLoadStart();

    void onFrameGet(Bitmap bitmap);

    void onMetaDataGet(Map<MetadataKey, String> metaData);

    void onLoadFailure(int code, String message);

}
