package com.github.congyh.model.message;

/**
 * 图片消息
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class ImageMessage extends AbstractMessage {
    // 图片链接(由微信服务器生成)
    private String picUrl;
    // 图片消息媒体id, 可以调用多媒体文件下载接口拉取数据
    private String mediaId;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
