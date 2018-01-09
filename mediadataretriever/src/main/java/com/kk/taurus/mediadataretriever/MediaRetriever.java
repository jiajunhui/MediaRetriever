package com.kk.taurus.mediadataretriever;

import android.content.Context;
import android.view.View;

import com.kk.taurus.mediadataretriever.cache.CacheManager;
import com.kk.taurus.mediadataretriever.cache.SettingBuilder;
import com.kk.taurus.mediadataretriever.core.MediaDataRetrieverTaskLoader;
import com.kk.taurus.mediadataretriever.entity.LoadTask;
import com.kk.taurus.mediadataretriever.entity.MetadataKey;
import com.kk.taurus.mediadataretriever.inter.IMediaMetadataRetriever;
import com.kk.taurus.mediadataretriever.inter.OnMediaRetrieverLoadListener;

import java.util.Map;

/**
 * Created by Taurus on 2017/12/7.
 */

public class MediaRetriever {

    public static final int MINI_KIND = 1;
    public static final int FULL_SCREEN_KIND = 2;
    public static final int MICRO_KIND = 3;

    public static void init(SettingBuilder settingBuilder){
        CacheManager.getInstance().init(settingBuilder);
    }

    private static void checkCacheInit(View view){
        if(CacheManager.getInstance().isHasInit())
            return;
        if(view==null)
            return;
        Context context = view.getContext();
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheMemory = maxMemory / 8;
        SettingBuilder settingBuilder = new SettingBuilder()
                .setContext(context)
                .setCacheDir(context.getExternalCacheDir())
                .setDiskCacheValueCount(1)
                .setMaxMemoryCacheSize(cacheMemory)
                .setMaxDiskCacheSize(200*1024*1024)
                .setCacheKeyProvider(new SettingBuilder.DefaultCacheKeyProvider());
        init(settingBuilder);
    }

    public static RetrieverBuilder withVideo(String dataSource) {
        return new RetrieverBuilder(IMediaMetadataRetriever.SOURCE_TYPE_VIDEO, dataSource);
    }

    public static RetrieverBuilder withAudio(String dataSource) {
        return new RetrieverBuilder(IMediaMetadataRetriever.SOURCE_TYPE_AUDIO, dataSource);
    }

    public static class RetrieverBuilder{

        private int sourceType = IMediaMetadataRetriever.SOURCE_TYPE_VIDEO;
        private String dataSource;
        private long time = -1;
        private int option = -1;
        private MetadataKey[] keys;
        private Map<String,String> headers;

        private int thumbnailType;

        private int placeHolder;
        private int errorHolder;

        public void into(View targetView) {
            into(targetView, null);
        }

        public void into(View targetView, final OnMediaRetrieverLoadListener onMediaRetrieverLoadListener) {
            checkCacheInit(targetView);
            createLoadTask(targetView, onMediaRetrieverLoadListener);
        }

        private void createLoadTask(View targetView, OnMediaRetrieverLoadListener onMediaRetrieverLoadListener) {
            LoadTask task = new LoadTask(
                    sourceType,
                    dataSource,
                    targetView,
                    placeHolder,
                    errorHolder,
                    time,
                    option,
                    thumbnailType,
                    keys,
                    headers,
                    onMediaRetrieverLoadListener);
            MediaDataRetrieverTaskLoader.load(task);
        }

        public void into(OnMediaRetrieverLoadListener onMediaRetrieverLoadListener) {
            createLoadTask(null, onMediaRetrieverLoadListener);
        }

        public RetrieverBuilder(int sourceType, String dataSource) {
            this.sourceType = sourceType;
            this.dataSource = dataSource;
        }

        public RetrieverBuilder frameAt(long time) {
            this.time = time;
            return this;
        }

        public RetrieverBuilder option(int option) {
            this.option = option;
            return this;
        }

        public RetrieverBuilder metaKeys(MetadataKey[] keys) {
            this.keys = keys;
            return this;
        }

        public RetrieverBuilder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public RetrieverBuilder thumbnailType(int thumbnailType) {
            this.thumbnailType = thumbnailType;
            return this;
        }

        public RetrieverBuilder placeHolder(int placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }

        public RetrieverBuilder errorHolder(int errorHolder) {
            this.errorHolder = errorHolder;
            return this;
        }
    }


}
