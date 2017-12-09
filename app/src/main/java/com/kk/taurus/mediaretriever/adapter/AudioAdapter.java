package com.kk.taurus.mediaretriever.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiajunhui.xapp.medialoader.bean.AudioItem;
import com.kk.taurus.mediadataretriever.MediaRetriever;
import com.kk.taurus.mediadataretriever.core.MetaKeys;
import com.kk.taurus.mediadataretriever.entity.MetaDataHashMap;
import com.kk.taurus.mediadataretriever.entity.MetadataKey;
import com.kk.taurus.mediadataretriever.callback.OnMediaRetrieverLoadCallback;
import com.kk.taurus.mediaretriever.R;
import com.kk.taurus.playerbase.utils.TimeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Taurus on 2017/12/7.
 */

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.AudioViewHolder>{

    private Context mContext;
    private List<AudioItem> mItems = new ArrayList<>();

    private MetadataKey key_duration = new MetadataKey(MetaKeys.SystemMetaKeys.METADATA_KEY_DURATION,"时长");
    private MetadataKey key_album = new MetadataKey(MetaKeys.SystemMetaKeys.METADATA_KEY_ALBUM,"专辑");

    private MetadataKey[] metadataKeys = new MetadataKey[]{
            key_duration,
            key_album
    };

    private Map<String,MetaDataHashMap> mMetaCache = new HashMap<>();

    public AudioAdapter(Context context, List<AudioItem> items){
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public AudioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AudioViewHolder(View.inflate(mContext,R.layout.item_audio,null));
    }

    @Override
    public void onBindViewHolder(final AudioViewHolder holder, int position) {
        final AudioItem item = getItem(position);
        Map<MetadataKey, String> cacheMeta = mMetaCache.get(item.getPath());
        holder.info.setText("");
        boolean needLoadMeta = true;
        if(cacheMeta!=null){
            StringBuilder sb = getBuilderInfo(cacheMeta, item);
            holder.info.setText(sb.toString());
            needLoadMeta = false;
        }

        holder.info.setTag(item.getPath());
        MediaRetriever.withAudio(item.getPath())
                .placeHolder(R.mipmap.ic_launcher)
                .metaKeys(needLoadMeta?metadataKeys:null).into(holder.cover, new OnMediaRetrieverLoadCallback() {
            @Override
            public void onMetaDataGet(Map<MetadataKey, String> metaData) {
                mMetaCache.put(item.getPath(), (MetaDataHashMap) metaData);
                StringBuilder sb = getBuilderInfo(metaData, item);
                if(item.getPath().equals(holder.info.getTag())){
                    holder.info.setText(sb.toString());
                }
            }
        });
    }

    private StringBuilder getBuilderInfo(Map<MetadataKey, String> metaData, AudioItem item){
        StringBuilder sb = new StringBuilder();
        String duration = metaData.get(key_duration);
        sb.append("音乐：").append(item.getDisplayName()).append("\n");
        sb.append("时长：").append(formatDuration(duration)).append("\n");
        sb.append("专辑：").append(metaData.get(key_album));
        return sb;
    }

    private String formatDuration(String duration){
        String result = "";
        try {
            long time = Long.parseLong(duration);
            result = TimeUtil.getTime(time);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private AudioItem getItem(int position){
        return mItems.get(position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class AudioViewHolder extends RecyclerView.ViewHolder{

        ImageView cover;
        TextView info;

        public AudioViewHolder(View itemView) {
            super(itemView);
            cover = (ImageView) itemView.findViewById(R.id.audio_cover);
            info = (TextView) itemView.findViewById(R.id.audio_info);
        }
    }

}
