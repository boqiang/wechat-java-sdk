package com.github.congyh.builder;

import com.github.congyh.model.WeChatXmlOutMessage;

import java.util.Date;

/**
 * 微信回复消息生成器
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public abstract class AbstractMessageBuilder<BuilderType extends AbstractMessageBuilder, MessageType> {
    /*注意: 当前这些属性还是builder持有的, 需要将其转化为被build的对象持有*/

    private String fromUserName;
    private String toUserName;

    @SuppressWarnings("unchecked")
    public BuilderType toUser(String toUserName) {
        this.toUserName = toUserName;
        return (BuilderType) this;
    }

    @SuppressWarnings("unchecked")
    public BuilderType fromUser(String fromUserName) {
        this.fromUserName = fromUserName;
        return (BuilderType) this;
    }

    /**
     * build之前的通用步骤
     *
     * @param message 服务器消息
     */
    public void preBuild(WeChatXmlOutMessage message) {
        message.setFromUserName(this.fromUserName);
        message.setToUserName(this.toUserName);
        message.setCreateTime(new Date().getTime());
    }

    public abstract MessageType build();
}
