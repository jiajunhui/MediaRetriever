package com.kk.taurus.mediadataretriever.inter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaDataSource;
import android.net.Uri;

import com.kk.taurus.mediadataretriever.entity.MetadataKey;

import java.io.FileDescriptor;
import java.util.Map;

/**
 * Created by Taurus on 2017/12/6.
 */

public interface IMediaMetadataRetriever {

    int SOURCE_TYPE_VIDEO = 1;
    int SOURCE_TYPE_AUDIO = 2;

    /**
     * This option is used with {@link IMediaMetadataRetriever#getFrameAtTime(long, int)} to retrieve
     * a sync (or keyFFmpeg) frame associated with a data source that is located
     * right before or at the given time.
     *
     * @see #getFrameAtTime(long, int)
     */
    int OPTION_PREVIOUS_SYNC    = 0x00;
    /**
     * This option is used with {@link #getFrameAtTime(long, int)} to retrieve
     * a sync (or keyFFmpeg) frame associated with a data source that is located
     * right after or at the given time.
     *
     * @see #getFrameAtTime(long, int)
     */
    int OPTION_NEXT_SYNC        = 0x01;
    /**
     * This option is used with {@link #getFrameAtTime(long, int)} to retrieve
     * a sync (or keyFFmpeg) frame associated with a data source that is located
     * closest to (in time) or at the given time.
     *
     * @see #getFrameAtTime(long, int)
     */
    int OPTION_CLOSEST_SYNC     = 0x02;
    /**
     * This option is used with {@link #getFrameAtTime(long, int)} to retrieve
     * a frame (not necessarily a keyFFmpeg frame) associated with a data source that
     * is located closest to or at the given time.
     *
     * @see #getFrameAtTime(long, int)
     */
    int OPTION_CLOSEST          = 0x03;
    

    void setDataSource(String path);

    void setDataSource(String uri, Map<String, String> headers);

    void setDataSource(FileDescriptor fd);

    void setDataSource(Context context, Uri uri);

    void setDataSource(MediaDataSource dataSource);

    String extractMetadata(MetadataKey metadataKey);

    Bitmap getFrameAtTime(long timeUs);

    Bitmap getFrameAtTime(long time, int option);

    Bitmap getFrameAtTime();

    byte[] getEmbeddedPicture();

    void release();

}
