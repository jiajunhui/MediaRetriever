package com.kk.taurus.mediadataretriever.entity;

import com.kk.taurus.mediadataretriever.inter.IMediaMetadataRetriever;

import java.util.Map;

/**
 * Created by Taurus on 2017/12/8.
 */

public class MediaTask {

    private int sourceType = IMediaMetadataRetriever.SOURCE_TYPE_VIDEO;
    private String dataSource;
    private long time = -1;
    private int option = -1;
    private MetadataKey[] keys;
    private Map<String,String> headers;
    private boolean loadFrame = true;

    public MediaTask() {
    }

    public MediaTask(int sourceType, String dataSource, long time, int option, MetadataKey[] keys, Map<String, String> headers, boolean loadFrame) {
        this.sourceType = sourceType;
        this.dataSource = dataSource;
        this.time = time;
        this.option = option;
        this.keys = keys;
        this.headers = headers;
        this.loadFrame = loadFrame;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public MetadataKey[] getKeys() {
        return keys;
    }

    public void setKeys(MetadataKey[] keys) {
        this.keys = keys;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public boolean isLoadFrame() {
        return loadFrame;
    }

    public void setLoadFrame(boolean loadFrame) {
        this.loadFrame = loadFrame;
    }

    public boolean isNeedLoadMetaData(){
        if(keys==null)
            return false;
        return keys.length>0;
    }

}
