package com.github.congyh.model;

import com.github.congyh.api.WeChatConst;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信公众平台回复消息
 *
 * <pre>
 * 包括所有回复消息类型的所有字段, 无需担心冗余字段, 在转换成xml的时候
 * {@code null}字段会自动被忽略.
 * </pre>
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 * @see WeChatXmlInMessage
 */
@XStreamAlias("xml")
public class WeChatXmlOutMessage extends AbstractMessage {
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
    // 事件类型: subscribe(订阅)、unsubscribe(取消订阅)
    @XStreamAlias("Event")
    private String event;
    // 事件KEY值, qrscene_为前缀, 后面为二维码的参数值
    @XStreamAlias("EventKey")
    private String eventKey;
    // 二维码的ticket, 可用来换取二维码图片
    @XStreamAlias("Ticket")
    private String ticket;

    public WeChatXmlOutMessage(WeChatXmlInMessage inMessage) {
        this.fromUserName = inMessage.getToUserName();
        this.toUserName = inMessage.getFromUserName();
        // debug
        this.msgType = WeChatConst.RESP_MESSAGE_TYPE_TEXT;
    }

    public WeChatXmlOutMessage() {}

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
