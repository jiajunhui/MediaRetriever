package com.kk.taurus.mediadataretriever.callback;

import com.kk.taurus.mediadataretriever.entity.LoadTask;
import com.kk.taurus.mediadataretriever.entity.MetadataKey;
import com.kk.taurus.mediadataretriever.inter.OnMediaRetrieverLoadListener;

import java.util.Map;

/**
 * Created by Taurus on 2017/12/8.
 */

public class LoadSuccessMetaDataCallback extends BaseLoadCallback {

    private Map<MetadataKey,String> metaData;

    public LoadSuccessMetaDataCallback(LoadTask task, Map<MetadataKey,String> metaData){
        super(task);
        this.metaData = metaData;
    }

    @Override
    public void run() {
        OnMediaRetrieverLoadListener mediaRetrieverLoadListener = getMediaRetrieverLoadListener();
        if(mediaRetrieverLoadListener!=null){
            mediaRetrieverLoadListener.onMetaDataGet(metaData);
        }
    }

}
