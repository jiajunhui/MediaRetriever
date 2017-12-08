package com.kk.taurus.mediadataretriever.callback;

import android.view.View;
import android.widget.ImageView;

import com.kk.taurus.mediadataretriever.entity.LoadTask;
import com.kk.taurus.mediadataretriever.inter.OnMediaRetrieverLoadListener;

/**
 * Created by Taurus on 2017/12/8.
 */

public class LoadStartCallback extends BaseLoadCallback {

    public LoadStartCallback(LoadTask task){
        super(task);
    }

    @Override
    public void run() {
        super.run();
        View targetView = mTask.getTargetView();
        if(targetView!=null && mTask.getPlaceHolder()>0 && mTask.isLegalTag() && targetView instanceof ImageView){
            ImageView imageView = (ImageView) targetView;
            imageView.setImageDrawable(targetView.getResources().getDrawable(mTask.getPlaceHolder()));
            imageView.refreshDrawableState();
        }
        OnMediaRetrieverLoadListener onMediaRetrieverLoadListener = getMediaRetrieverLoadListener();
        if(onMediaRetrieverLoadListener==null)
            return;
        onMediaRetrieverLoadListener.onLoadStart();
    }
}
