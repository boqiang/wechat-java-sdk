package com.github.congyh.api;

import com.github.congyh.api.impl.DemoOAuth2Handler;
import com.github.congyh.api.impl.SimpleTextHandler;
import com.github.congyh.model.WeChatXmlInMessage;

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
public final class WeChatMessageRouter {
    // 规则列表
    private static final List<WeChatMessageRouteRule> rules = new LinkedList<>();

    static {
        // 规则越细的越要放在前面
        addRule().withMsgType(WeChatConst.REQ_MESSAGE_TYPE_TEXT)
            .withContent("OAuth测试")
            .useHandler(new DemoOAuth2Handler())
            .endRule();
        addRule().withMsgType(WeChatConst.REQ_MESSAGE_TYPE_TEXT)
            .useHandler(new SimpleTextHandler())
            .endRule();
    }

    private WeChatMessageRouter() {}

    /**
     * 构造一个新的路由规则
     *
     * @return 消息路由规则
     */
    public static WeChatMessageRouteRule addRule() {
        return new WeChatMessageRouteRule();
    }

    /**
     * 将服务器消息路由到合适的handler进行处理
     *
     * @param inMessage 服务器消息
     * @return 消息handler
     */
    public static WeChatMessageHandler route(final WeChatXmlInMessage inMessage) {
        // 如果是重复消息, 不进行处理
        if (WeChatDuplicateMessageDetector.detectDuplicate(inMessage)) {
            return null;
        }

        for (final WeChatMessageRouteRule rule: rules) {
            if (rule.match(inMessage)) {
                return rule.getHandler();
            }
        }

        return null;
    }

    public static List<WeChatMessageRouteRule> getRules() {
        return rules;
    }
}
