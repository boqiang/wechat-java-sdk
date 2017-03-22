package com.github.congyh.api;

import com.github.congyh.model.WeChatXmlInMessage;
import com.github.congyh.model.WeChatXmlOutMessage;

/**
 * 服务器消息消息处理器
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public abstract class WeChatMessageHandler {
    private WeChatMessageRouteRule rule;
    public abstract WeChatXmlOutMessage handle(WeChatXmlInMessage inMessage);

    public WeChatMessageRouteRule getRule() {
        return rule;
    }

    public void setRule(WeChatMessageRouteRule rule) {
        this.rule = rule;
    }
}
