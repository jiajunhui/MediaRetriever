package com.kk.taurus.mediadataretriever.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.kk.taurus.mediadataretriever.entity.MediaData;
import com.kk.taurus.mediadataretriever.entity.MediaTask;
import com.kk.taurus.mediadataretriever.entity.MetaDataHashMap;
import com.kk.taurus.mediadataretriever.entity.MetadataKey;
import com.kk.taurus.mediadataretriever.inter.IMediaMetadataRetriever;
import com.kk.taurus.mediadataretriever.utils.Utils;

import java.util.Map;

/**
 * Created by Taurus on 2017/12/6.
 */

public class MediaMetadataRetrieverLoaderManager {

    private final String TAG = "LoaderManager";

    private MediaMetadataRetrieverLoaderManager(){}

    private static MediaMetadataRetrieverLoaderManager instance;

    public static MediaMetadataRetrieverLoaderManager get(){
        if(null==instance){
            synchronized (MediaMetadataRetrieverLoaderManager.class){
                if(null==instance){
                    instance = new MediaMetadataRetrieverLoaderManager();
                }
            }
        }
        return instance;
    }

    private boolean isMetaHasData(Map<MetadataKey,String> metaData){
        return metaData!=null && metaData.size()>0;
    }

    public MediaData getMediaData(MediaTask task){
        return getMediaData(task, null);
    }

    public MediaData getMediaData(MediaTask task, OnLoadListener onLoadListener){
        return systemLoadMediaData(task, onLoadListener);
    }

    private MediaData systemLoadMediaData(MediaTask task, OnLoadListener onLoadListener){
        IMediaMetadataRetriever mediaMetadataRetriever = new SystemMediaMetadataRetriever();
        MediaData data = loadMediaData(mediaMetadataRetriever,task, onLoadListener);
        mediaMetadataRetriever.release();
        return data;
    }

    private MediaData loadMediaData(IMediaMetadataRetriever mediaMetadataRetriever, MediaTask task, OnLoadListener onLoadListener){
        MediaData data = new MediaData();
        Map<String, String> headers = task.getHeaders();
        String dataSource = task.getDataSource();
        if(Utils.isLocalFile(dataSource)){
            mediaMetadataRetriever.setDataSource(dataSource);
        }else{
            mediaMetadataRetriever.setDataSource(dataSource,headers);
        }
        if(task.isLoadFrame()){
            long time = task.getTime();
            int option = task.getOption();
            Bitmap bitmap = null;
            if(task.getSourceType()== IMediaMetadataRetriever.SOURCE_TYPE_VIDEO){
                bitmap = mediaMetadataRetriever.getFrameAtTime(time,option);
            }else{
                byte[] bytes = mediaMetadataRetriever.getEmbeddedPicture();
                if(bytes!=null){
                    bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length); //转换为图片
                }
            }
            //set frame data
            data.setFrame(bitmap);
            if(onLoadListener!=null){
                onLoadListener.onFrameGet(bitmap);
            }
        }
        if(task.isNeedLoadMetaData()){
            //set meta data
            Map<MetadataKey, String> loadMetaData = attachMetaKeys(mediaMetadataRetriever, task.getKeys());
            data.setMetaData(loadMetaData);
            if(onLoadListener!=null){
                onLoadListener.onMetaDataGet(loadMetaData);
            }
        }
        return data;
    }

    private Map<MetadataKey, String> attachMetaKeys(IMediaMetadataRetriever mediaMetadataRetriever, MetadataKey[] keys){
        if(keys==null)
            return null;
        Map<MetadataKey, String> result = new MetaDataHashMap();
        for(MetadataKey key:keys){
            result.put(key, mediaMetadataRetriever.extractMetadata(key));
        }
        return result;
    }

    public interface OnLoadListener{
        void onFrameGet(Bitmap bitmap);
        void onMetaDataGet(Map<MetadataKey,String> metaData);
    }

}
