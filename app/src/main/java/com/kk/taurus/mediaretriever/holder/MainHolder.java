package com.kk.taurus.mediaretriever.holder;

import android.content.Context;
import android.widget.RadioGroup;

import com.kk.taurus.mediaretriever.R;
import com.kk.taurus.uiframe.i.HolderData;
import com.kk.taurus.uiframe.listener.OnHolderListener;
import com.kk.taurus.uiframe.v.ContentHolder;

/**
 * Created by Taurus on 2017/12/7.
 */

public class MainHolder extends ContentHolder<HolderData> {

    public static final int EVENT_CODE_MAIN_HOLDER_LOCAL = 1;
    public static final int EVENT_CODE_MAIN_HOLDER_ONLINE = 2;
    public static final int EVENT_CODE_MAIN_HOLDER_AUDIO = 3;

    private RadioGroup mRadioGroup;

    public MainHolder(Context context) {
        super(context);
    }

    public MainHolder(Context context, OnHolderListener onHolderListener) {
        super(context, onHolderListener);
    }

    @Override
    public void onCreate() {
        setContentView(R.layout.activity_main);
        mRadioGroup = getViewById(R.id.radioGroup);
    }

    @Override
    public void onHolderCreated() {
        super.onHolderCreated();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_local_video:
                        onHolderEvent(EVENT_CODE_MAIN_HOLDER_LOCAL,null);
                        break;
                    case R.id.rb_online_video:
                        onHolderEvent(EVENT_CODE_MAIN_HOLDER_ONLINE,null);
                        break;
                    case R.id.rb_audio:
                        onHolderEvent(EVENT_CODE_MAIN_HOLDER_AUDIO,null);
                        break;
                }
            }
        });
    }

    public int getContainerId(){
        return R.id.container;
    }
}
