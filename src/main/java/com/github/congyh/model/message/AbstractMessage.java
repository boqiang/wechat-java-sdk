package com.github.congyh.model.message;

/**
 * 消息基类, 代表两种类型的消息
 * <ul>
 *     <li>接受消息: 开发者从用户那里接收到的普通消息(
 *     实际上是由微信服务器POST过来的)</li>
 *     <li>发送消息: 开发者向用户发送消息响应</li>
 * </ul>
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public abstract class AbstractMessage {
    // 开发者微信号(接收时) or 发送方帐号（一个OpenID）(发送时)
    private String ToUserName;
    // 发送方帐号（一个OpenID）(接收时) or 开发者微信号(发送时)
    private String FromUserName;
    // 消息创建时间 （整型）
    private int CreateTime;
    // 消息类型
    private String MsgType;
    // 消息id，64位整型
    private long MsgId;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        this.ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.FromUserName = fromUserName;
    }

    public int getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(int createTime) {
        this.CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        this.MsgType = msgType;
    }

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        this.MsgId = msgId;
    }
}
