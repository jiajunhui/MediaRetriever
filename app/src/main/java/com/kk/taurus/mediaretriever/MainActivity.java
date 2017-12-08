package com.kk.taurus.mediaretriever;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.kk.taurus.mediaretriever.holder.MainHolder;
import com.kk.taurus.mediaretriever.ui.AudioFragment;
import com.kk.taurus.mediaretriever.ui.LocalVideoFragment;
import com.kk.taurus.mediaretriever.ui.OnLineVideoFragment;
import com.kk.taurus.uiframe.a.TitleBarActivity;
import com.kk.taurus.uiframe.i.HolderData;
import com.kk.taurus.uiframe.v.BaseTitleBarHolder;
import com.kk.taurus.uiframe.v.BaseUserHolder;
import com.kk.taurus.uiframe.v.ContentHolder;
import com.kk.taurus.uiframe.v.d.DefaultTitleBarHolder;

public class MainActivity extends TitleBarActivity<HolderData,MainHolder> {

    private LocalVideoFragment mLocalVideoFragment;
    private OnLineVideoFragment mOnLineVideoFragment;
    private AudioFragment mAudioFragment;

    @Override
    public ContentHolder onBindContentHolder() {
        return new MainHolder(this,this);
    }

    @Override
    protected void onInit(Bundle savedInstanceState) {
        super.onInit(savedInstanceState);
        getTitleBarHolder().setBackIconVisibility(View.GONE);
        getTitleBarHolder().setTitle("多媒体信息获取");
        showLocalFragment();
    }

    private DefaultTitleBarHolder getTitleBarHolder(){
        BaseUserHolder userHolder = getUserHolder();
        BaseTitleBarHolder titleBarHolder = userHolder.titleBarHolder;
        return (DefaultTitleBarHolder)titleBarHolder;
    }

    @Override
    public void onHolderEvent(int eventCode, Bundle bundle) {
        super.onHolderEvent(eventCode, bundle);
        switch (eventCode){
            case MainHolder.EVENT_CODE_MAIN_HOLDER_LOCAL:
                showLocalFragment();
                break;
            case MainHolder.EVENT_CODE_MAIN_HOLDER_ONLINE:
                showOnLineFragment();
                break;
            case MainHolder.EVENT_CODE_MAIN_HOLDER_AUDIO:
                showAudioFragment();
                break;
        }
    }

    private void showLocalFragment(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hiddenOnLineFragment(ft);
        hiddenAudioFragment(ft);
        if(mLocalVideoFragment==null){
            mLocalVideoFragment = new LocalVideoFragment();
            ft.add(getUserContentHolder().getContainerId(),mLocalVideoFragment);
        }else{
            ft.show(mLocalVideoFragment);
        }
        ft.commitAllowingStateLoss();
    }

    private void showOnLineFragment(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hiddenLocalFragment(ft);
        hiddenAudioFragment(ft);
        if(mOnLineVideoFragment==null){
            mOnLineVideoFragment = new OnLineVideoFragment();
            ft.add(getUserContentHolder().getContainerId(),mOnLineVideoFragment);
        }else{
            ft.show(mOnLineVideoFragment);
        }
        ft.commitAllowingStateLoss();
    }

    private void showAudioFragment(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hiddenLocalFragment(ft);
        hiddenOnLineFragment(ft);
        if(mAudioFragment==null){
            mAudioFragment = new AudioFragment();
            ft.add(getUserContentHolder().getContainerId(),mAudioFragment);
        }else{
            ft.show(mAudioFragment);
        }
        ft.commitAllowingStateLoss();
    }

    private void hiddenLocalFragment(FragmentTransaction ft){
        if(mLocalVideoFragment!=null){
            ft.hide(mLocalVideoFragment);
        }
    }

    private void hiddenOnLineFragment(FragmentTransaction ft){
        if(mOnLineVideoFragment!=null){
            ft.hide(mOnLineVideoFragment);
        }
    }

    private void hiddenAudioFragment(FragmentTransaction ft){
        if(mAudioFragment!=null){
            ft.hide(mAudioFragment);
        }
    }
}
