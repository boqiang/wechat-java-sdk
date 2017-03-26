package com.github.congyh.api;

import com.github.congyh.model.WeChatXmlInMessage;
import com.github.congyh.model.WeChatXmlOutMessage;
import com.github.congyh.service.WeChatMessageRouteRule;

/**
 * 服务器消息消息处理器
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public abstract class WeChatMessageHandler {
    private WeChatMessageRouteRule rule;

    /**
     * 处理服务器消息
     *
     * @param inMessage 服务器消息
     * @param sessionManager 内含所有session对象
     * @return 响应消息
     */
    public abstract WeChatXmlOutMessage handle(WeChatXmlInMessage inMessage,
                                               WeChatSessionManager sessionManager);

    public WeChatMessageRouteRule getRule() {
        return rule;
    }

    public void setRule(WeChatMessageRouteRule rule) {
        this.rule = rule;
    }
}
