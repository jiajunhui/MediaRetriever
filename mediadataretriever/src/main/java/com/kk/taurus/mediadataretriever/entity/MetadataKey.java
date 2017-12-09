package com.kk.taurus.mediadataretriever.entity;

import android.text.TextUtils;

/**
 * Created by Taurus on 2017/12/7.
 */

public class MetadataKey {

    public int keyInt;
    public String keyString;
    public String desc;

    public MetadataKey(int keyInt) {
        this.keyInt = keyInt;
    }

    public MetadataKey(String keyString) {
        this.keyString = keyString;
    }

    public MetadataKey(int keyInt, String desc) {
        this.keyInt = keyInt;
        this.desc = desc;
    }

    public MetadataKey(String keyString, String desc) {
        this.keyString = keyString;
        this.desc = desc;
    }

    public MetadataKey(int keyInt, String keyString, String desc) {
        this.keyInt = keyInt;
        this.keyString = keyString;
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
