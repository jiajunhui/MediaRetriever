package com.kk.taurus.mediadataretriever.core;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.kk.taurus.mediadataretriever.cache.CacheManager;
import com.kk.taurus.mediadataretriever.callback.LoadFailureCallback;
import com.kk.taurus.mediadataretriever.callback.LoadStartCallback;
import com.kk.taurus.mediadataretriever.callback.LoadSuccessFrameCallback;
import com.kk.taurus.mediadataretriever.callback.LoadSuccessMetaDataCallback;
import com.kk.taurus.mediadataretriever.entity.LoadTask;
import com.kk.taurus.mediadataretriever.entity.MediaData;
import com.kk.taurus.mediadataretriever.entity.MetadataKey;
import com.kk.taurus.mediadataretriever.inter.OnMediaRetrieverLoadListener;
import com.kk.taurus.mediadataretriever.utils.Utils;
import com.kk.taurus.threadpool.DefaultThreadManager;
import com.kk.taurus.threadpool.ExecutorSetting;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by Taurus on 2017/12/6.
 */

public class MediaDataRetrieverTaskLoader {

    private static final String TAG = "TaskLoader";
    private static Semaphore mThreadSemaphore;
    private static LinkedList<LoadTask> mTaskQueue;
    private static final int MSG_CODE_CHECK_TASK = 1;
    private static boolean hasInitDispatchThread;
    private static MyHandler mHandler;
    private static Handler mCallBackHandler = new Handler(Looper.getMainLooper());
    private static int processorsNumber;

    static {
        processorsNumber = Runtime.getRuntime().availableProcessors();
        mTaskQueue = new LinkedList<>();
        ExecutorSetting executorSetting = new ExecutorSetting();
        executorSetting.setCorePoolSize(processorsNumber + 1);
        executorSetting.setMaximumPoolSize(20);
        executorSetting.setKeepAliveTime(30);
        executorSetting.setUnit(TimeUnit.SECONDS);
        DefaultThreadManager.getInstance((executorSetting));
    }

    public static MediaData loadMediaData(LoadTask invokeTask,boolean loadFrame, MediaMetadataRetrieverLoaderManager.OnLoadListener onLoadListener){
        return MediaMetadataRetrieverLoaderManager.get().getMediaData(invokeTask.generatorMediaTask(loadFrame), onLoadListener);
    }

    public static void load(final LoadTask task){
        if(mThreadSemaphore==null){
            mThreadSemaphore = new Semaphore(processorsNumber + 1);
            Log.d(TAG,"ThreadSemaphore **init** availablePermits = " + mThreadSemaphore.availablePermits());
        }
        callBackOnLoadStart(task);
        if(!hasInitDispatchThread){
            initDispatchThread(new OnInitListener() {
                @Override
                public void onInited() {
                    dispatchTaskLogic(task);
                }
            });
        }else{
            dispatchTaskLogic(task);
        }
    }

    private static void notifyPollTask(){
        mHandler.sendEmptyMessage(MSG_CODE_CHECK_TASK);
    }

    private static void addQueue(LoadTask task){
        mTaskQueue.add(task);
    }

    private static void dispatchTaskLogic(LoadTask task){
        Bitmap lruCache = CacheManager.getInstance().getLruCache(task.getDataSource());
        if(lruCache!=null){
            callBackOnFrameGet(task,lruCache);
            task.setNeedLoadFrame(false);
            if(!task.isNeedLoadMetaData()){
                return;
            }
        }
        addQueue(task);
        notifyPollTask();
    }

    private static void notifyLoad(LoadTask task){
        //获得许可
        semaphoreAcquire();
        DefaultThreadManager.getInstance().execute(new TaskRunnable(task) {
            @Override
            public void run() {
                super.run();
                final LoadTask invokeTask = getInvokeTask();

                final String dataSource = invokeTask.getDataSource();

                final int thumbnailType = invokeTask.getThumbnailType();

                if(invokeTask.isNeedLoadFrame()){

                    Bitmap result = CacheManager.getInstance().getDiskCache(dataSource);

                    if(result!=null){
                        result = Utils.scaleBitmap(result,thumbnailType);
                        callBackOnFrameGet(invokeTask,result);
                        CacheManager.getInstance().putLruCache(dataSource, result);
                        //从磁盘缓存拿到数据后，将帧画面加载标记为不要
                        invokeTask.setNeedLoadFrame(false);
                    }
                }

                loadMediaData(invokeTask, invokeTask.isNeedLoadFrame(), new MediaMetadataRetrieverLoaderManager.OnLoadListener() {
                    @Override
                    public void onFrameGet(Bitmap bitmap) {
                        if(bitmap!=null){
                            bitmap = Utils.scaleBitmap(bitmap,thumbnailType);
                            callBackOnFrameGet(invokeTask,bitmap);
                            CacheManager.getInstance().putLruCache(dataSource, bitmap);
                            CacheManager.getInstance().putDiskCache(dataSource,bitmap);
                        }else{
                            callBackOnLoadFailure(invokeTask,OnMediaRetrieverLoadListener.CODE_FAILURE_FRAME_GET,"frame load failure!");
                        }
                    }

                    @Override
                    public void onMetaDataGet(Map<MetadataKey, String> metaData) {
                        if(metaData!=null&&metaData.size()>0){
                            callBackOnMetaDataGet(invokeTask,metaData);
                        }else{
                            callBackOnLoadFailure(invokeTask,OnMediaRetrieverLoadListener.CODE_FAILURE_META_DATA_GET,"meta data load failure!");
                        }
                    }
                });

                semaphoreRelease();
            }
        });
    }

    /**
     * 获得许可
     */
    private static void semaphoreAcquire(){
        try {
            mThreadSemaphore.acquire();
            Log.d(TAG,"ThreadSemaphore >>acquire<< availablePermits = " + mThreadSemaphore.availablePermits());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放许可
     */
    private static void semaphoreRelease(){
        mThreadSemaphore.release();
        Log.d(TAG,"ThreadSemaphore <<release>> availablePermits = " + mThreadSemaphore.availablePermits());
    }

    private static void callBackOnLoadStart(final LoadTask invokeTask) {
        mCallBackHandler.post(new LoadStartCallback(invokeTask));
    }

    private static void callBackOnFrameGet(final LoadTask invokeTask, final Bitmap bitmap) {
        Log.d(TAG,"callback onFrame get : " + invokeTask.getDataSource());
        mCallBackHandler.post(new LoadSuccessFrameCallback(invokeTask,bitmap));
    }

    private static void callBackOnMetaDataGet(final LoadTask invokeTask, final Map<MetadataKey, String> metaData) {
        mCallBackHandler.post(new LoadSuccessMetaDataCallback(invokeTask,metaData));
    }

    private static void callBackOnLoadFailure(final LoadTask invokeTask, final int code, final String message) {
        mCallBackHandler.post(new LoadFailureCallback(invokeTask,code,message));
    }

    private static synchronized void initDispatchThread(final OnInitListener onInitListener){

        new Thread(){
            @Override
            public void run() {
                super.run();
                Looper.prepare();
                mHandler = new MyHandler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what){
                            case MSG_CODE_CHECK_TASK:
                                pollTask();
                                break;
                        }
                    }
                };
                hasInitDispatchThread = true;
                onInitListener.onInited();
                Looper.loop();
            }
        }.start();
    }

    private static int getAvailablePermits(){
        return mThreadSemaphore.availablePermits();
    }

    private synchronized static void pollTask() {
        LoadTask task;
        Log.d(TAG,"=====TaskQueue size = " + mTaskQueue.size());
        if(getAvailablePermits()<=0){
            task = mTaskQueue.pollLast();
            Log.d(TAG,"---> TaskQueue poll <<LAST>>");
        }else{
            task = mTaskQueue.pollFirst();
            Log.d(TAG,"---> TaskQueue poll <<FIRST>>");
        }
        if(task==null)
            return;
        notifyLoad(task);
    }

    public interface OnInitListener{
        void onInited();
    }

    private static class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    }

    public static class TaskRunnable implements Runnable{

        private LoadTask invokeTask;

        public TaskRunnable(LoadTask task){
            this.invokeTask = task;
        }

        public LoadTask getInvokeTask(){
            return invokeTask;
        }

        @Override
        public void run() {

        }
    }

}
