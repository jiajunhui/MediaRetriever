package com.kk.taurus.mediadataretriever.core;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaDataSource;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;

import com.kk.taurus.mediadataretriever.entity.MetadataKey;
import com.kk.taurus.mediadataretriever.inter.IMediaMetadataRetriever;

import java.io.FileDescriptor;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Taurus on 2017/12/6.
 */

public class SystemMediaMetadataRetriever implements IMediaMetadataRetriever {

    private MediaMetadataRetriever mMediaMetadataRetriever;

    public SystemMediaMetadataRetriever(){
        mMediaMetadataRetriever = new MediaMetadataRetriever();
    }

    @Override
    public void setDataSource(String uri, Map<String, String> headers) {
        if(headers==null)
            headers = new HashMap<>();
        mMediaMetadataRetriever.setDataSource(uri,headers);
    }

    @Override
    public void setDataSource(String path) {
        mMediaMetadataRetriever.setDataSource(path);
    }

    @Override
    public void setDataSource(FileDescriptor fd) {
        mMediaMetadataRetriever.setDataSource(fd);
    }

    @Override
    public void setDataSource(Context context, Uri uri) {
        mMediaMetadataRetriever.setDataSource(context, uri);
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void setDataSource(MediaDataSource dataSource) {
        mMediaMetadataRetriever.setDataSource(dataSource);
    }

    @Override
    public String extractMetadata(MetadataKey keyCode) {
        return mMediaMetadataRetriever.extractMetadata(keyCode.keyInt);
    }

    @Override
    public Bitmap getFrameAtTime(long time, int option) {
        if(option==-1)
            option = OPTION_CLOSEST_SYNC;
        return mMediaMetadataRetriever.getFrameAtTime(time, option);
    }

    @Override
    public Bitmap getFrameAtTime(long timeUs) {
        return getFrameAtTime(timeUs, OPTION_CLOSEST_SYNC);
    }

    @Override
    public Bitmap getFrameAtTime() {
        return getFrameAtTime(-1, OPTION_CLOSEST_SYNC);
    }

    @Override
    public byte[] getEmbeddedPicture() {
        return mMediaMetadataRetriever.getEmbeddedPicture();
    }

    @Override
    public void release() {
        mMediaMetadataRetriever.release();
    }
}
