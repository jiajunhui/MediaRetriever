package com.kk.taurus.mediadataretriever.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by Taurus on 2017/6/2.
 */

public class LruCacheManager {

    private LruCache<String,Bitmap> mLruCache;

    public LruCacheManager(SettingBuilder settingBuilder){
        //LruCache的配置
        mLruCache = new LruCache<String, Bitmap>(settingBuilder.getMaxMemoryCacheSize()){
            @Override
            protected int sizeOf(String key, Bitmap value){
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    /**
     * 加入或更新缓存
     * @param key
     * @param bitmap
     * @return
     */
    public Bitmap putCache(String key, Bitmap bitmap){
        Bitmap bitmapValue = getCache(key);
        if(bitmapValue==null){
            if(mLruCache!=null&&bitmap!=null)
                bitmapValue= mLruCache.put(key, bitmap);
        }
        return bitmapValue;
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public Bitmap getCache(String key){
        if(mLruCache!=null){
            return mLruCache.get(key);
        }
        return null;
    }

    /**
     * 清空缓存
     */
    public void deleteCache(){
        if(mLruCache!=null)
            mLruCache.evictAll();
    }

    /**
     * 移除指定的缓存
     * @param key
     */
    public void removeCache(String key){
        if(mLruCache!=null)
            mLruCache.remove(key);
    }

    /**
     * 当前缓存大小
     * @return
     */
    public int size(){
        int size=0;
        if(mLruCache!=null)
            size+=mLruCache.size();
        return size;
    }

}
