package com.kk.taurus.mediadataretriever.entity;

import android.view.View;

import com.kk.taurus.mediadataretriever.inter.IMediaMetadataRetriever;
import com.kk.taurus.mediadataretriever.inter.OnMediaRetrieverLoadListener;

import java.util.Map;

/**
 * Created by Taurus on 2017/12/6.
 */

public class LoadTask {

    private int sourceType = IMediaMetadataRetriever.SOURCE_TYPE_VIDEO;
    private String dataSource;
    private View targetView;
    private int placeHolder;
    private int errorHolder;
    private long time = -1;
    private int option = -1;
    private int thumbnailType;
    private MetadataKey[] keys;
    private Map<String,String> headers;
    private OnMediaRetrieverLoadListener onMediaRetrieverLoadListener;
    private long createTime;

    private boolean needLoadFrame = true;

    public LoadTask(int sourceType, String dataSource, View targetView, int placeHolder, int errorHolder, long time, int option, int thumbnailType, MetadataKey[] keys, Map<String, String> headers, OnMediaRetrieverLoadListener onMediaRetrieverLoadListener) {
        this.sourceType = sourceType;
        this.dataSource = dataSource;
        this.targetView = targetView;
        this.placeHolder = placeHolder;
        this.errorHolder = errorHolder;
        this.time = time;
        this.option = option;
        this.thumbnailType = thumbnailType;
        this.keys = keys;
        this.headers = headers;
        this.onMediaRetrieverLoadListener = onMediaRetrieverLoadListener;
        createTime = System.currentTimeMillis();
        if(this.targetView!=null){
            this.targetView.setTag(dataSource);
        }
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

    public View getTargetView() {
        return targetView;
    }

    public void setTargetView(View targetView) {
        this.targetView = targetView;
    }

    public int getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(int placeHolder) {
        this.placeHolder = placeHolder;
    }

    public int getErrorHolder() {
        return errorHolder;
    }

    public void setErrorHolder(int errorHolder) {
        this.errorHolder = errorHolder;
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

    public int getThumbnailType() {
        return thumbnailType;
    }

    public void setThumbnailType(int thumbnailType) {
        this.thumbnailType = thumbnailType;
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

    public OnMediaRetrieverLoadListener getOnMediaRetrieverLoadListener() {
        return onMediaRetrieverLoadListener;
    }

    public void setOnMediaRetrieverLoadListener(OnMediaRetrieverLoadListener onMediaRetrieverLoadListener) {
        this.onMediaRetrieverLoadListener = onMediaRetrieverLoadListener;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public boolean isNeedLoadFrame() {
        return needLoadFrame;
    }

    public void setNeedLoadFrame(boolean needLoadFrame) {
        this.needLoadFrame = needLoadFrame;
    }

    public Object getTag(){
        return dataSource;
    }

    public boolean isLegalTag(){
        Object viewTag = targetView.getTag();
        if(getTag()==null || viewTag==null)
            return false;
        return getTag().equals(viewTag);
    }

    public boolean isNeedLoadMetaData(){
        if(keys==null)
            return false;
        return keys.length>0;
    }

    public MediaTask generatorMediaTask(boolean loadFrame){
        return new MediaTask(sourceType,dataSource,time,option,keys,headers,loadFrame);
    }

}
