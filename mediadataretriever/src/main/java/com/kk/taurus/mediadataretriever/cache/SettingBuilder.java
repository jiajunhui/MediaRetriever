package com.kk.taurus.mediadataretriever.cache;

import android.content.Context;

import com.kk.taurus.mediadataretriever.utils.MD5Util;

import java.io.File;

/**
 * Created by Taurus on 2017/6/2.
 */

public class SettingBuilder {

    public static final int DEFAULT_DISK_CACHE_VALUE_COUNT = 100;

    public static final long DEFAULT_MAX_CACHE_SIZE = 200*1024*1024;

    private Context context;
    private File cacheDir;
    private int defaultPlaceHolder;
    private int diskCacheValueCount = DEFAULT_DISK_CACHE_VALUE_COUNT;
    private int maxMemoryCacheSize;
    private long maxDiskCacheSize = DEFAULT_MAX_CACHE_SIZE;
    private CacheKeyProvider cacheKeyProvider;

    public Context getContext() {
        return context;
    }

    public SettingBuilder setContext(Context context) {
        this.context = context;
        return this;
    }

    public File getCacheDir() {
        return cacheDir;
    }

    public SettingBuilder setCacheDir(File cacheDir) {
        this.cacheDir = cacheDir;
        return this;
    }

    public int getDefaultPlaceHolder() {
        return defaultPlaceHolder;
    }

    public SettingBuilder setDefaultPlaceHolder(int defaultPlaceHolder) {
        this.defaultPlaceHolder = defaultPlaceHolder;
        return this;
    }

    public int getDiskCacheValueCount() {
        return diskCacheValueCount;
    }

    public SettingBuilder setDiskCacheValueCount(int diskCacheValueCount) {
        this.diskCacheValueCount = diskCacheValueCount;
        return this;
    }

    public int getMaxMemoryCacheSize() {
        return maxMemoryCacheSize;
    }

    public SettingBuilder setMaxMemoryCacheSize(int maxMemoryCacheSize) {
        this.maxMemoryCacheSize = maxMemoryCacheSize;
        return this;
    }

    public long getMaxDiskCacheSize() {
        return maxDiskCacheSize;
    }

    public SettingBuilder setMaxDiskCacheSize(long maxDiskCacheSize) {
        this.maxDiskCacheSize = maxDiskCacheSize;
        return this;
    }

    public CacheKeyProvider getCacheKeyProvider() {
        return cacheKeyProvider;
    }

    public SettingBuilder setCacheKeyProvider(CacheKeyProvider cacheKeyProvider) {
        this.cacheKeyProvider = cacheKeyProvider;
        return this;
    }

    public static class DefaultCacheKeyProvider implements CacheKeyProvider {
        @Override
        public String getCacheKey(String url) {
            return MD5Util.md5(url);
        }
    }

    public interface CacheKeyProvider {
        String getCacheKey(String url);
    }

}
