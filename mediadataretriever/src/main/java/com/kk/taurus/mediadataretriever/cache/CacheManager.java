package com.kk.taurus.mediadataretriever.cache;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.File;

/**
 * Created by Taurus on 2017/5/31.
 */

public class CacheManager {

    private final String TAG = "CacheManager";
    private static CacheManager instance;
    private CacheManager(){}

    private DiskCacheManager mDiskCacheManager;
    private LruCacheManager mLruCacheManager;

    public static CacheManager getInstance(){
        if(null==instance){
            synchronized (CacheManager.class){
                if(null==instance){
                    instance = new CacheManager();
                }
            }
        }
        return instance;
    }

    public boolean isHasInit(){
        return mDiskCacheManager!=null && mLruCacheManager!=null;
    }

    public void init(SettingBuilder settingBuilder){
        mDiskCacheManager = new DiskCacheManager(settingBuilder);
        mLruCacheManager = new LruCacheManager(settingBuilder);
    }

    public Bitmap getLruCache(String key){
        Log.d(TAG,"lru_size : " + mLruCacheManager.size());
        return mLruCacheManager.getCache(key);
    }

    public Bitmap putLruCache(String key, Bitmap value){
        return mLruCacheManager.putCache(key, value);
    }

    public boolean putDiskCache(String key, Bitmap bitmap){
        return mDiskCacheManager.putDiskCache(key, bitmap);
    }

    public File getDiskCacheDirectory(){
        return mDiskCacheManager.getCacheDirectory();
    }

    public Bitmap getDiskCache(String key){
        return mDiskCacheManager.getDiskCache(key);
    }

}
