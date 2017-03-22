package com.github.congyh.api;

import com.github.congyh.model.WeChatXmlInMessage;
import com.github.congyh.model.WeChatXmlOutMessage;

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

            }
        }
    }

    /**
     * 具体的路由规则
     */
    private static class WeChatMessageRouteRule {
        private String msgType;
        private String event;
        private String eventKey;
        private boolean async = true; // 默认启用异步处理
        private WeChatMessageRouter router = WeChatMessageRouter.getRouter();

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

        /**
         * 标志着添加完了一个rule, 但可以用builder模式连续添加rule
         *
         * @return {@link WeChatMessageRouter WeChatMessageRouter} 单例对象
         */
        public WeChatMessageRouter endRule() {
           this.router.rules.add(this);
            return this.router;
        }

        /**
         * 判断服务器消息是否与当前路由规则相匹配
         *
         * @param inMessage 服务器消息
         * @return
         */
        public boolean match(WeChatXmlInMessage inMessage) {

        }
    }
}
