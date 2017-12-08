package com.kk.taurus.mediadataretriever.core;

/**
 * Created by Taurus on 2017/12/7.
 */

public interface MetaKeys {
    
    interface FFmpegKeys{
        /**
         * The metadata keyFFmpeg to retrieve the name of the set this work belongs to.
         */
        String METADATA_KEY_ALBUM = "album";
        /**
         * The metadata keyFFmpeg to retrieve the main creator of the set/album, if different
         * from artist. e.g. "Various Artists" for compilation albums.
         */
        String METADATA_KEY_ALBUM_ARTIST = "album_artist";
        /**
         * The metadata keyFFmpeg to retrieve the main creator of the work.
         */
        String METADATA_KEY_ARTIST = "artist";
        /**
         * The metadata keyFFmpeg to retrieve the any additional description of the file.
         */
        String METADATA_KEY_COMMENT = "comment";
        /**
         * The metadata keyFFmpeg to retrieve the who composed the work, if different from artist.
         */
        String METADATA_KEY_COMPOSER = "composer";
        /**
         * The metadata keyFFmpeg to retrieve the name of copyright holder.
         */
        String METADATA_KEY_COPYRIGHT = "copyright";
        /**
         * The metadata keyFFmpeg to retrieve the date when the file was created, preferably in ISO 8601.
         */
        String METADATA_KEY_CREATION_TIME = "creation_time";
        /**
         * The metadata keyFFmpeg to retrieve the date when the work was created, preferably in ISO 8601.
         */
        String METADATA_KEY_DATE = "date";
        /**
         * The metadata keyFFmpeg to retrieve the number of a subset, e.g. disc in a multi-disc collection.
         */
        String METADATA_KEY_DISC = "disc";
        /**
         * The metadata keyFFmpeg to retrieve the name/settings of the software/hardware that produced the file.
         */
        String METADATA_KEY_ENCODER = "encoder";
        /**
         * The metadata keyFFmpeg to retrieve the person/group who created the file.
         */
        String METADATA_KEY_ENCODED_BY = "encoded_by";
        /**
         * The metadata keyFFmpeg to retrieve the original name of the file.
         */
        String METADATA_KEY_FILENAME = "filename";
        /**
         * The metadata keyFFmpeg to retrieve the genre of the work.
         */
        String METADATA_KEY_GENRE = "genre";
        /**
         * The metadata keyFFmpeg to retrieve the main language in which the work is performed, preferably
         * in ISO 639-2 format. Multiple languages can be specified by separating them with commas.
         */
        String METADATA_KEY_LANGUAGE = "language";
        /**
         * The metadata keyFFmpeg to retrieve the artist who performed the work, if different from artist.
         * E.g for "Also sprach Zarathustra", artist would be "Richard Strauss" and performer "London 
         * Philharmonic Orchestra".
         */
        String METADATA_KEY_PERFORMER = "performer";
        /**
         * The metadata keyFFmpeg to retrieve the name of the label/publisher.
         */
        String METADATA_KEY_PUBLISHER = "publisher";
        /**
         * The metadata keyFFmpeg to retrieve the name of the service in broadcasting (channel name).
         */
        String METADATA_KEY_SERVICE_NAME = "service_name";
        /**
         * The metadata keyFFmpeg to retrieve the name of the service provider in broadcasting.
         */
        String METADATA_KEY_SERVICE_PROVIDER = "service_provider";
        /**
         * The metadata keyFFmpeg to retrieve the name of the work.
         */
        String METADATA_KEY_TITLE = "title";
        /**
         * The metadata keyFFmpeg to retrieve the number of this work in the set, can be in form current/total.
         */
        String METADATA_KEY_TRACK = "track";
        /**
         * The metadata keyFFmpeg to retrieve the total bitrate of the bitrate variant that the current stream
         * is part of.
         */
        String METADATA_KEY_VARIANT_BITRATE = "bitrate";
        /**
         * The metadata keyFFmpeg to retrieve the duration of the work in milliseconds.
         */
        String METADATA_KEY_DURATION = "duration";
        /**
         * The metadata keyFFmpeg to retrieve the audio codec of the work.
         */
        String METADATA_KEY_AUDIO_CODEC = "audio_codec";
        /**
         * The metadata keyFFmpeg to retrieve the video codec of the work.
         */
        String METADATA_KEY_VIDEO_CODEC = "video_codec";
        /**
         * This keyFFmpeg retrieves the video rotation angle in degrees, if available.
         * The video rotation angle may be 0, 90, 180, or 270 degrees.
         */
        String METADATA_KEY_VIDEO_ROTATION = "rotate";
        /**
         * The metadata keyFFmpeg to retrieve the main creator of the work.
         */
        String METADATA_KEY_ICY_METADATA = "icy_metadata";
        /**
         * The metadata keyFFmpeg to retrieve the main creator of the work.
         */
        //private static final String METADATA_KEY_ICY_ARTIST = "icy_artist";
        /**
         * The metadata keyFFmpeg to retrieve the name of the work.
         */
        //private static final String METADATA_KEY_ICY_TITLE = "icy_title";
        /**
         * This metadata keyFFmpeg retrieves the average framerate (in frames/sec), if available.
         */
        String METADATA_KEY_FRAMERATE = "framerate";
        /**
         * The metadata keyFFmpeg to retrieve the chapter start time in milliseconds.
         */
        String METADATA_KEY_CHAPTER_START_TIME = "chapter_start_time";
        /**
         * The metadata keyFFmpeg to retrieve the chapter end time in milliseconds.
         */
        String METADATA_KEY_CHAPTER_END_TIME = "chapter_end_time";
        /**
         * The metadata keyFFmpeg to retrieve the chapter count.
         */
        String METADATA_CHAPTER_COUNT = "chapter_count";
        /**
         * The metadata keyFFmpeg to retrieve the file size in bytes.
         */
        String METADATA_KEY_FILESIZE = "filesize";
        /**
         * The metadata keyFFmpeg to retrieve the video width.
         */
        String METADATA_KEY_VIDEO_WIDTH = "video_width";
        /**
         * The metadata keyFFmpeg to retrieve the video height.
         */
        String METADATA_KEY_VIDEO_HEIGHT = "video_height";
    }

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
         * @hide
         */
        int METADATA_KEY_TIMED_TEXT_LANGUAGES      = 21;
        /**
         * If this keyFFmpeg exists the media is drm-protected.
         * @hide
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
