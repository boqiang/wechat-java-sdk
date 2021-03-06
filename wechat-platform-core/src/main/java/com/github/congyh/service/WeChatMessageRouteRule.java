package com.github.congyh.service;



import com.github.congyh.api.WeChatMessageHandler;
import com.github.congyh.model.WeChatXmlInMessage;

/**
 * 自定义路由规则
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public final class WeChatMessageRouteRule {
    private String msgType;
    private String event;
    private String eventKey;
    private boolean async = true; // 默认启用异步处理
    private String content;
    private WeChatMessageHandler handler;

    public  WeChatMessageRouteRule withMsgType(final String msgType) {
        this.msgType = msgType;
        return this;
    }

    public WeChatMessageRouteRule withEvent(final String event) {
        this.event = event;
        return this;
    }

    public WeChatMessageRouteRule withEventKey(final String eventKey) {
        this.eventKey = eventKey;
        return this;
    }

    public WeChatMessageRouteRule withAsync(final boolean async) {
        this.async = async;
        return this;
    }

    public WeChatMessageRouteRule withContent(final String content) {
        this.content = content;
        return this;
    }

    public WeChatMessageRouteRule useHandler(final WeChatMessageHandler handler) {
        this.handler = handler;
        handler.setRule(this);
        return this;
    }

    /**
     * 标志着添加完了一个rule, 但可以用builder模式连续添加rule
     *
     * @return {@link WeChatMessageRouter WeChatMessageRouter} 单例对象
     */
    public Class<WeChatMessageRouter> endRule() {
        WeChatMessageRouter.getRules().add(this);
        return WeChatMessageRouter.class;
    }

    /**
     * 判断服务器消息是否与当前路由规则相匹配
     *
     * @param inMessage 服务器消息
     * @return true 如果匹配 or false 如果不匹配
     */
    public boolean match(WeChatXmlInMessage inMessage) {
        // 必须定义的所有规则都匹配才算匹配规则
        return (this.msgType == null
            || this.msgType.toLowerCase().equals(
            (inMessage.getMsgType()==null?null:inMessage.getMsgType().toLowerCase())))
            && (this.event == null
            || this.event.toLowerCase().equals(
            (inMessage.getEvent()==null?null:inMessage.getEvent().toLowerCase())))
            && (this.eventKey == null
            || this.eventKey.toLowerCase().equals(
            (inMessage.getEventKey()==null?null:inMessage.getEventKey().toLowerCase())))
            && (this.content == null
            || this.content.toLowerCase().equals(
            (inMessage.getContent()==null?null:inMessage.getContent().toLowerCase())));
    }

    public WeChatMessageHandler getHandler() {
        return handler;
    }

    public boolean isAsync() {
        return async;
    }
}