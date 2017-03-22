package com.github.congyh.api;

import com.github.congyh.model.WeChatXmlInMessage;
import com.github.congyh.model.WeChatXmlOutMessage;
import com.github.congyh.service.WeChatMessageHandler;

import java.util.LinkedList;
import java.util.List;

/**
 * 微信消息路由器
 *
 * <pre>
 * 通过持有一个{@link  WeChatMessageRouteRule WeChatMessageRouteRule}list, 记录
 * 服务器消息到的handler的路由规则.
 * </pre>
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class WeChatMessageRouter {
    private static final WeChatMessageRouter INSTANCE = new WeChatMessageRouter();
    // 规则列表
    private final List<WeChatMessageRouteRule> rules = new LinkedList<>();

    private WeChatMessageRouter() {}

    public static WeChatMessageRouter getRouter() {
        return INSTANCE;
    }
    /**
     * 构造一个新的路由规则
     *
     * @return
     */
    public WeChatMessageRouteRule addRule() {
        return new WeChatMessageRouteRule();
    }

    /**
     * 将服务器消息路由到合适的handler进行处理
     *
     * @param inMessage 服务器消息
     * @return 消息handler
     */
    public WeChatMessageHandler route(final WeChatXmlInMessage inMessage) {
        for (final WeChatMessageRouteRule rule: this.rules) {
            if (rule.match(inMessage)) {
                return rule.getHandler();
            }
        }
        // TODO 没有找到匹配的handler, 怎么处理?
        return null;
    }

    public List<WeChatMessageRouteRule> getRules() {
        return rules;
    }
}
