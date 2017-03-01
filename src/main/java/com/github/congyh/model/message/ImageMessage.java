package com.github.congyh.model.message;

/**
 * 图片消息
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class ImageMessage extends AbstractMessage {
    // 图片链接(由微信服务器生成)
    private String PicUrl;
    // 图片消息媒体id, 可以调用多媒体文件下载接口拉取数据
    private String MediaId;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        this.PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        this.MediaId = mediaId;
    }
}
