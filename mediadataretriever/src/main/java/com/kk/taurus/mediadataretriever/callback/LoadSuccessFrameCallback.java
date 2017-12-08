package com.kk.taurus.mediadataretriever.callback;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.kk.taurus.mediadataretriever.entity.LoadTask;
import com.kk.taurus.mediadataretriever.inter.OnMediaRetrieverLoadListener;

/**
 * Created by Taurus on 2017/12/8.
 */

public class LoadSuccessFrameCallback extends BaseLoadCallback {

    private Bitmap bitmap;

    public LoadSuccessFrameCallback(LoadTask task, Bitmap bitmap){
        super(task);
        this.bitmap = bitmap;
    }

    @Override
    public void run() {
        View targetView = mTask.getTargetView();
        if(targetView instanceof ImageView){
            if(mTask.isLegalTag()){
                ImageView view = ((ImageView)targetView);
                Drawable resource = new BitmapDrawable(view.getContext().getResources(),bitmap);
                view.setImageDrawable(resource);
            }
        }
        OnMediaRetrieverLoadListener mediaRetrieverLoadListener = getMediaRetrieverLoadListener();
        if(mediaRetrieverLoadListener!=null){
            mediaRetrieverLoadListener.onFrameGet(bitmap);
        }
    }

}
