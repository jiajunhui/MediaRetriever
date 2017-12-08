package com.kk.taurus.mediadataretriever.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.kk.taurus.mediadataretriever.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Taurus on 2017/6/2.
 */

public class DiskCacheManager {

    private DiskLruCache mDiskLruCache;
    private SettingBuilder settingBuilder;

    public DiskCacheManager(SettingBuilder settingBuilder){
        this(settingBuilder.getCacheDir(), Utils.getAppVersion(settingBuilder.getContext()),settingBuilder.getDiskCacheValueCount(),settingBuilder.getMaxDiskCacheSize());
        this.settingBuilder = settingBuilder;
    }

    private DiskCacheManager(File cacheDirectory, int appVersion, int valueCount, long maxSize){
        try {
            if(!cacheDirectory.exists()){
                cacheDirectory.mkdirs();
            }
            //DiskLruCache的配置
            mDiskLruCache = DiskLruCache.open(cacheDirectory, appVersion, valueCount, maxSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getCacheDirectory(){
        return settingBuilder.getCacheDir();
    }

    private String getCacheKey(String key){
        return settingBuilder.getCacheKeyProvider().getCacheKey(key);
    }

    /**
     * 加入或更新缓存
     * @param key
     * @param bitmap
     * @return
     */
    public boolean putDiskCache(String key, Bitmap bitmap){
        DiskLruCache.Editor editor = null;
        try {
            String md5Key = getCacheKey(key);
            if(mDiskLruCache.get(md5Key)!=null){
                return true;
            }
            editor = mDiskLruCache.edit(md5Key);
            if(editor!=null){
                OutputStream outputStream= editor.newOutputStream(0);
                Utils.Bitmap2OutputStream(bitmap,outputStream);
                if(outputStream!=null){
                    editor.commit();
                    return true;
                }
                else {
                    editor.abort();
                    return false;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            if(editor!=null){
                try {
                    editor.abort();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 刷新缓存
     */
    public void flushCache() {
        if (mDiskLruCache != null) {
            try {
                mDiskLruCache.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public Bitmap getDiskCache(String key){
        String md5Key = getCacheKey(key);
        Bitmap bitmap=null;
        try {
            if(mDiskLruCache!=null){
                DiskLruCache.Snapshot snapshot = mDiskLruCache.get(md5Key);
                if(snapshot!=null){
                    bitmap= BitmapFactory.decodeStream(snapshot.getInputStream(0)) ;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 清空缓存
     */
    public void deleteDiskCache(){
        try {
            if(mDiskLruCache!=null){
                mDiskLruCache.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 移除指定的缓存
     * @param key
     */
    public void removeDiskCache(String key){
        if(mDiskLruCache!=null){
            try {
                mDiskLruCache.remove(key);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取当前缓存的大小
     * @return
     */
    public int size(){
        int size=0;
        if(mDiskLruCache!=null){
            size=(int) mDiskLruCache.size();
        }
        return size;
    }

    /**
     * 关闭缓存
     */
    public void close(){
        if(mDiskLruCache!=null){
            try {
                mDiskLruCache.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
