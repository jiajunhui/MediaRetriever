package com.kk.taurus.mediadataretriever.entity;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Taurus on 2017/12/8.
 */

public class MediaData implements Serializable{

    private Bitmap frame;
    private Map<MetadataKey,String> metaData;

    public MediaData() {
    }

    public MediaData(Bitmap frame, Map<MetadataKey, String> metaData) {
        this.frame = frame;
        this.metaData = metaData;
    }

    public Bitmap getFrame() {
        return frame;
    }

    public void setFrame(Bitmap frame) {
        this.frame = frame;
    }

    public Map<MetadataKey, String> getMetaData() {
        return metaData;
    }

    public void setMetaData(Map<MetadataKey, String> metaData) {
        this.metaData = metaData;
    }
}
