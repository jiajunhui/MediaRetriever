package com.kk.taurus.mediadataretriever.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaDataSource;
import android.net.Uri;

import com.kk.taurus.mediadataretriever.entity.MetadataKey;
import com.kk.taurus.mediadataretriever.inter.IMediaMetadataRetriever;

import java.io.FileDescriptor;
import java.util.Map;

import wseemann.media.FFmpegMediaMetadataRetriever;

/**
 * Created by Taurus on 2017/12/6.
 */

public class FFMPEGMediaMetadataRetriever implements IMediaMetadataRetriever {

    private FFmpegMediaMetadataRetriever mFFmpegMediaMetadataRetriever;

    public FFMPEGMediaMetadataRetriever(){
        mFFmpegMediaMetadataRetriever = new FFmpegMediaMetadataRetriever();
    }

    @Override
    public void setDataSource(String uri, Map<String, String> headers) {
        mFFmpegMediaMetadataRetriever.setDataSource(uri);
    }

    @Override
    public void setDataSource(String path) {
        mFFmpegMediaMetadataRetriever.setDataSource(path);
    }

    @Override
    public void setDataSource(FileDescriptor fd) {
        mFFmpegMediaMetadataRetriever.setDataSource(fd);
    }

    @Override
    public void setDataSource(Context context, Uri uri) {
        mFFmpegMediaMetadataRetriever.setDataSource(context, uri);
    }

    @Override
    public void setDataSource(MediaDataSource dataSource) {
        //FFmpegMediaMetadataRetriever without this method
    }

    @Override
    public String extractMetadata(MetadataKey key) {
        return mFFmpegMediaMetadataRetriever.extractMetadata(key.keyFFmpeg);
    }

    @Override
    public Bitmap getFrameAtTime(long time, int option) {
        if(option==-1)
            option = OPTION_CLOSEST_SYNC;
        return mFFmpegMediaMetadataRetriever.getFrameAtTime(time, option);
    }

    @Override
    public Bitmap getFrameAtTime(long timeUs) {
        return getFrameAtTime(-1, OPTION_CLOSEST_SYNC);
    }

    @Override
    public Bitmap getFrameAtTime() {
        return getFrameAtTime(-1, OPTION_CLOSEST_SYNC);
    }

    @Override
    public byte[] getEmbeddedPicture() {
        return mFFmpegMediaMetadataRetriever.getEmbeddedPicture();
    }

    @Override
    public void release() {
        mFFmpegMediaMetadataRetriever.release();
    }
}
