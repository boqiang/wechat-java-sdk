package com.github.congyh.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@XStreamAlias("xml")
public abstract class AbstractMessage {
    // 开发者微信号(接收时) or 发送方帐号（一个OpenID）(发送时)
    @XStreamAlias("ToUserName")
    protected String toUserName;
    // 发送方帐号（一个OpenID）(接收时) or 开发者微信号(发送时)
    @XStreamAlias("FromUserName")
    protected String fromUserName;
    // 消息创建时间 （整型）
    @XStreamAlias("CreateTime")
    protected Long createTime;
    // 消息类型
    @XStreamAlias("MsgType")
    protected String msgType;

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
}
