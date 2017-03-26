package com.github.congyh.service;

import com.github.congyh.api.WeChatConst;
import com.github.congyh.api.WeChatDuplicateMessageDetector;
import com.github.congyh.api.impl.BasicDuplicateMessageDetector;
import com.github.congyh.api.impl.DemoOAuth2Handler;
import com.github.congyh.api.impl.NotSupportedMessageTypeHandler;
import com.github.congyh.api.impl.SimpleTextHandler;
import com.github.congyh.model.WeChatXmlInMessage;
import com.github.congyh.model.WeChatXmlOutMessage;
import com.github.congyh.api.impl.BasicSessionManager;
import com.github.congyh.api.WeChatSessionManager;

import java.util.LinkedList;
import java.util.List;

/**
 * 微信消息路由器
 *
 * <pre>
 * 1. 通过持有一个{@link  WeChatMessageRouteRule WeChatMessageRouteRule}list, 记录
 * 服务器消息到的handler的路由规则.
 * 2. 消息的路由是服务器消息处理的起点, 由于所有消息都会经过这里, 故将sessionManager
 * 与这里绑定;
 * 3. 因为所有消息都会经过这里, 所以消息排重器也与这里绑定.
 *
 * 你可以为router提供自己的sessionManager和duplicateMessageDetector实现.
 * </pre>
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public final class WeChatMessageRouter {
    // 规则列表
    private static final List<WeChatMessageRouteRule> rules = new LinkedList<>();
    // 由于WeChatMessageRouter是单例的, 所以WeChatSessionManager不主动创建, 也是单例的
    private static WeChatSessionManager sessionManager = new BasicSessionManager();
    // 由于WeChatMessageRouter是单例的, 所以WeChatSessionManager不主动创建, 也是单例的
    private static WeChatDuplicateMessageDetector duplicateMessageDetector
         = new BasicDuplicateMessageDetector();

    static {
        // debug, 内置规则, 规则越细的越要放在前面
        // TODO 后期将规则设置整合到WeChatService中去.
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
     * @return 回复消息
     */
    public static WeChatXmlOutMessage route(final WeChatXmlInMessage inMessage) {
        // 如果是重复消息, 不进行处理
        if (duplicateMessageDetector.isMessageDuplicate(inMessage)) {
            return null;
        }
        // 进行session关联
        sessionManager.getSession(inMessage.getFromUserName(), true);

        for (final WeChatMessageRouteRule rule: rules) {
            if (rule.match(inMessage)) {
                return rule.getHandler().handle(inMessage, sessionManager);
            }
        }

        return new NotSupportedMessageTypeHandler().handle(inMessage, sessionManager);
    }

    public static List<WeChatMessageRouteRule> getRules() {
        return rules;
    }

    public static void setSessionManager(WeChatSessionManager sessionManager) {
        WeChatMessageRouter.sessionManager = sessionManager;
    }

    public static void setDuplicateMessageDetector(WeChatDuplicateMessageDetector duplicateMessageDetector) {
        // 如果原消息判重器是内置的BasicDuplicateMessageDetector类型, 需要手动停止线程运作
        if (WeChatMessageRouter.duplicateMessageDetector instanceof BasicDuplicateMessageDetector) {
            ((BasicDuplicateMessageDetector) WeChatMessageRouter.duplicateMessageDetector)
                .endScheduledDetect();
        }
        WeChatMessageRouter.duplicateMessageDetector = duplicateMessageDetector;
    }
}
