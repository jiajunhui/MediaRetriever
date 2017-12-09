package com.kk.taurus.mediaretriever.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiajunhui.xapp.medialoader.bean.VideoItem;
import com.kk.taurus.mediadataretriever.MediaRetriever;
import com.kk.taurus.mediadataretriever.core.MetaKeys;
import com.kk.taurus.mediadataretriever.entity.MetaDataHashMap;
import com.kk.taurus.mediadataretriever.entity.MetadataKey;
import com.kk.taurus.mediadataretriever.callback.OnMediaRetrieverLoadCallback;
import com.kk.taurus.mediadataretriever.utils.TimeUtil;
import com.kk.taurus.mediaretriever.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Taurus on 2017/12/7.
 */

public class LocalVideoAdapter extends RecyclerView.Adapter<LocalVideoAdapter.LocalVideoItemHolder>{

    private Context mContext;
    private List<VideoItem> mVideoItems = new ArrayList<>();

    private MetadataKey key_duration = new MetadataKey(MetaKeys.SystemMetaKeys.METADATA_KEY_DURATION,"时长");
    private MetadataKey key_width = new MetadataKey(MetaKeys.SystemMetaKeys.METADATA_KEY_VIDEO_WIDTH,"宽度");
    private MetadataKey key_height = new MetadataKey(MetaKeys.SystemMetaKeys.METADATA_KEY_VIDEO_HEIGHT,"高度");

    private MetadataKey[] metadataKeys = new MetadataKey[]{
            key_duration,
            key_width,
            key_height
    };

    private Map<String,MetaDataHashMap> mMetaCache = new HashMap<>();

    public LocalVideoAdapter(Context context, List<VideoItem> items){
        this.mContext = context;
        this.mVideoItems = items;
    }

    @Override
    public LocalVideoItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LocalVideoItemHolder(View.inflate(mContext,R.layout.item_local_video,null));
    }

    @Override
    public void onBindViewHolder(final LocalVideoItemHolder holder, int position) {
        final VideoItem item = getItem(position);
        Map<MetadataKey, String> cacheMeta = mMetaCache.get(item.getPath());
        holder.info.setText("");
        boolean needLoadMeta = true;
        if(cacheMeta!=null){
            StringBuilder sb = getBuilderInfo(cacheMeta, item);
            holder.info.setText(sb.toString());
            needLoadMeta = false;
        }

        holder.info.setTag(item.getPath());
        MediaRetriever.withVideo(item.getPath())
                .thumbnailType(MediaRetriever.MINI_KIND)
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

    private StringBuilder getBuilderInfo(Map<MetadataKey, String> metaData, VideoItem item){
        StringBuilder sb = new StringBuilder();
        String duration = metaData.get(key_duration);
        sb.append("视频：").append(item.getDisplayName()).append("\n");
        sb.append("时长：").append(formatDuration(duration)).append("\n");
        sb.append("宽度：").append(metaData.get(key_width)).append("\n");
        sb.append("高度：").append(metaData.get(key_height));
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

    private VideoItem getItem(int position){
        return mVideoItems.get(position);
    }

    @Override
    public int getItemCount() {
        return mVideoItems.size();
    }

    public static class LocalVideoItemHolder extends RecyclerView.ViewHolder{

        ImageView cover;
        TextView info;

        public LocalVideoItemHolder(View itemView) {
            super(itemView);
            cover = (ImageView) itemView.findViewById(R.id.video_cover);
            info = (TextView) itemView.findViewById(R.id.tv_video_info);
        }
    }

}
