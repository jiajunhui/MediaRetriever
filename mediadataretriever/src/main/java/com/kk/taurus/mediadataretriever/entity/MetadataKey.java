package com.kk.taurus.mediadataretriever.entity;

import android.text.TextUtils;

/**
 * Created by Taurus on 2017/12/7.
 */

public class MetadataKey {

    public int keySystem;
    public String keyFFmpeg;
    public String desc;

    public MetadataKey(int keySystem) {
        this.keySystem = keySystem;
    }

    public MetadataKey(String keyFFmpeg) {
        this.keyFFmpeg = keyFFmpeg;
    }

    public MetadataKey(int keySystem, String desc) {
        this.keySystem = keySystem;
        this.desc = desc;
    }

    public MetadataKey(String keyFFmpeg, String desc) {
        this.keyFFmpeg = keyFFmpeg;
        this.desc = desc;
    }

    public MetadataKey(int keySystem, String keyFFmpeg, String desc) {
        this.keySystem = keySystem;
        this.keyFFmpeg = keyFFmpeg;
        this.desc = desc;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(!TextUtils.isEmpty(desc)){
            sb.append(desc);
        }
        return sb.toString();
    }
}
