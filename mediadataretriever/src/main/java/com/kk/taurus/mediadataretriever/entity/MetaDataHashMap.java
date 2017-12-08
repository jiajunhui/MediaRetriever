package com.kk.taurus.mediadataretriever.entity;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Taurus on 2017/12/7.
 */

public class MetaDataHashMap extends HashMap<MetadataKey, String> {

    @Override
    public String toString() {
        Set<Entry<MetadataKey, String>> entries = entrySet();
        StringBuilder sb = new StringBuilder();
        int size = entries.size();
        int count = 0;
        for(Map.Entry<MetadataKey,String> entry:entries){
            if(TextUtils.isEmpty(entry.getValue())){
                count++;
                continue;
            }
            sb.append(entry.getKey().toString()).append(entry.getValue()).append(count==size-1?"":"\n");
            count++;
        }
        return sb.toString();
    }

}
