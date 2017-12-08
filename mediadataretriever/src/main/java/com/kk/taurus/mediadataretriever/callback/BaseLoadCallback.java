package com.kk.taurus.mediadataretriever.callback;

import com.kk.taurus.mediadataretriever.entity.LoadTask;
import com.kk.taurus.mediadataretriever.inter.OnMediaRetrieverLoadListener;

/**
 * Created by Taurus on 2017/12/8.
 */

public class BaseLoadCallback implements Runnable {

    protected LoadTask mTask;

    public BaseLoadCallback(LoadTask task){
        this.mTask = task;
    }

    protected OnMediaRetrieverLoadListener getMediaRetrieverLoadListener(){
        return mTask.getOnMediaRetrieverLoadListener();
    }

    @Override
    public void run() {

    }
}
