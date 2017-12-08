# MediaRetriever
一个音视频封面、缩略图、多媒体信息加载工具

<br><br>
![image](https://github.com/jiajunhui/MediaRetriever/raw/master/screenshot/screenshot01.jpg)
![image](https://github.com/jiajunhui/MediaRetriever/raw/master/screenshot/screenshot02.jpg)
![image](https://github.com/jiajunhui/MediaRetriever/raw/master/screenshot/screenshot03.jpg)

# 用法

```java
//load video info
MediaRetriever
        .withVideo(url)
        .thumbnailType(MediaRetriever.MINI_KIND)
        .placeHolder(R.mipmap.ic_launcher)
        .metaKeys(metadataKeys)
        .into(view);

//load audio
MediaRetriever
        .withAudio(url)
        .thumbnailType(MediaRetriever.MINI_KIND)
        .placeHolder(R.mipmap.ic_launcher)
        .metaKeys(metadataKeys)
        .into(view);

//load meta info
MediaRetriever.withVideo(url).metaKeys(metadataKeys).into(new OnMediaRetrieverLoadCallback() {
        @Override
        public void onMetaDataGet(Map<MetadataKey, String> metaData) {
            //...
        }
    });
```