package com.github.congyh.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信公众平台消息
 *
 * <p>包含了公众平台所有消息中的所有字段类型(随微信版本仍在不断更新中),
 * 之所以统一整合在这里, 是为了使用XStream来解析xml时, 保证所有可能的标签
 * 都能被解析(信息中未包含的标签会自动设置为null).
 *
 * <p>倘若将各种消息类型进行细分, 需要在xml解析过程添加额外的逻辑, 影响
 * 程序效率, 且不易维护.
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@XStreamAlias("xml")
public class WeChatXmlInMessage extends AbstractMessage {
    // 开发者微信号(接收时) or 发送方帐号（一个OpenID）(发送时)
    @XStreamAlias("ToUserName")
    private String toUserName;
    // 发送方帐号（一个OpenID）(接收时) or 开发者微信号(发送时)
    @XStreamAlias("FromUserName")
    private String fromUserName;
    // 消息创建时间 （整型）
    @XStreamAlias("CreateTime")
    private Long createTime;
    // 消息类型
    @XStreamAlias("MsgType")
    private String msgType;
    // 消息id, 64位整型
    @XStreamAlias("MsgId")
    private Long msgId;
    // 文本消息类型
    @XStreamAlias("Content")
    private String content;
    // 图片链接(由微信服务器生成)
    @XStreamAlias("PicUrl")
    private String picUrl;
    // 图片消息媒体id, 可以调用多媒体文件下载接口拉取数据
    @XStreamAlias("MediaId")
    private String mediaId;
    // 语音格式, 如amr, peex等
    @XStreamAlias("Format")
    private String format;
    // 事件类型: subscribe(订阅)、unsubscribe(取消订阅)
    @XStreamAlias("Event")
    private String event;
    // 事件KEY值, qrscene_为前缀, 后面为二维码的参数值
    @XStreamAlias("EventKey")
    private String eventKey;
    // 二维码的ticket, 可用来换取二维码图片
    @XStreamAlias("Ticket")
    private String ticket;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
