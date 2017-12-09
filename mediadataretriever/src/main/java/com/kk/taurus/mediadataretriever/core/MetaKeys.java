package com.kk.taurus.mediadataretriever.core;

/**
 * Created by Taurus on 2017/12/7.
 */

public interface MetaKeys {

    interface SystemMetaKeys{
        /**
         * The metadata keyFFmpeg to retrieve the numeric string describing the
         * order of the audio data source on its original recording.
         */
        int METADATA_KEY_CD_TRACK_NUMBER = 0;
        /**
         * The metadata keyFFmpeg to retrieve the information about the album title
         * of the data source.
         */
        int METADATA_KEY_ALBUM           = 1;
        /**
         * The metadata keyFFmpeg to retrieve the information about the artist of
         * the data source.
         */
        int METADATA_KEY_ARTIST          = 2;
        /**
         * The metadata keyFFmpeg to retrieve the information about the author of
         * the data source.
         */
        int METADATA_KEY_AUTHOR          = 3;
        /**
         * The metadata keyFFmpeg to retrieve the information about the composer of
         * the data source.
         */
        int METADATA_KEY_COMPOSER        = 4;
        /**
         * The metadata keyFFmpeg to retrieve the date when the data source was created
         * or modified.
         */
        int METADATA_KEY_DATE            = 5;
        /**
         * The metadata keyFFmpeg to retrieve the content type or genre of the data
         * source.
         */
        int METADATA_KEY_GENRE           = 6;
        /**
         * The metadata keyFFmpeg to retrieve the data source title.
         */
        int METADATA_KEY_TITLE           = 7;
        /**
         * The metadata keyFFmpeg to retrieve the year when the data source was created
         * or modified.
         */
        int METADATA_KEY_YEAR            = 8;
        /**
         * The metadata keyFFmpeg to retrieve the playback duration of the data source.
         */
        int METADATA_KEY_DURATION        = 9;
        /**
         * The metadata keyFFmpeg to retrieve the number of tracks, such as audio, video,
         * text, in the data source, such as a mp4 or 3gpp file.
         */
        int METADATA_KEY_NUM_TRACKS      = 10;
        /**
         * The metadata keyFFmpeg to retrieve the information of the writer (such as
         * lyricist) of the data source.
         */
        int METADATA_KEY_WRITER          = 11;
        /**
         * The metadata keyFFmpeg to retrieve the mime type of the data source. Some
         * example mime types include: "video/mp4", "audio/mp4", "audio/amr-wb",
         * etc.
         */
        int METADATA_KEY_MIMETYPE        = 12;
        /**
         * The metadata keyFFmpeg to retrieve the information about the performers or
         * artist associated with the data source.
         */
        int METADATA_KEY_ALBUMARTIST     = 13;
        /**
         * The metadata keyFFmpeg to retrieve the numberic string that describes which
         * part of a set the audio data source comes from.
         */
        int METADATA_KEY_DISC_NUMBER     = 14;
        /**
         * The metadata keyFFmpeg to retrieve the music album compilation status.
         */
        int METADATA_KEY_COMPILATION     = 15;
        /**
         * If this keyFFmpeg exists the media contains audio content.
         */
        int METADATA_KEY_HAS_AUDIO       = 16;
        /**
         * If this keyFFmpeg exists the media contains video content.
         */
        int METADATA_KEY_HAS_VIDEO       = 17;
        /**
         * If the media contains video, this keyFFmpeg retrieves its width.
         */
        int METADATA_KEY_VIDEO_WIDTH     = 18;
        /**
         * If the media contains video, this keyFFmpeg retrieves its height.
         */
        int METADATA_KEY_VIDEO_HEIGHT    = 19;
        /**
         * This keyFFmpeg retrieves the average bitrate (in bits/sec), if available.
         */
        int METADATA_KEY_BITRATE         = 20;
        /**
         * This keyFFmpeg retrieves the language code of text tracks, if available.
         * If multiple text tracks present, the return value will look like:
         * "eng:chi"
         * 
         */
        int METADATA_KEY_TIMED_TEXT_LANGUAGES      = 21;
        /**
         * If this keyFFmpeg exists the media is drm-protected.
         * 
         */
        int METADATA_KEY_IS_DRM          = 22;
        /**
         * This keyFFmpeg retrieves the location information, if available.
         * The location should be specified according to ISO-6709 standard, under
         * a mp4/3gp box "@xyz". Location with longitude of -90 degrees and latitude
         * of 180 degrees will be retrieved as "-90.0000+180.0000", for instance.
         */
        int METADATA_KEY_LOCATION        = 23;
        /**
         * This keyFFmpeg retrieves the video rotation angle in degrees, if available.
         * The video rotation angle may be 0, 90, 180, or 270 degrees.
         */
        int METADATA_KEY_VIDEO_ROTATION = 24;
        /**
         * This keyFFmpeg retrieves the original capture framerate, if it's
         * available. The capture framerate will be a floating point
         * number.
         */
        int METADATA_KEY_CAPTURE_FRAMERATE = 25;
    }
    
}
