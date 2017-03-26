package com.github.congyh.demo.api.impl;

import com.github.congyh.api.WeChatMessageHandler;
import com.github.congyh.api.WeChatSessionManager;
import com.github.congyh.builder.WeChatMessageBuilderFactory;
import com.github.congyh.model.WeChatXmlInMessage;
import com.github.congyh.model.WeChatXmlOutMessage;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class DemoSimpleTextHandler extends WeChatMessageHandler {
    @Override
    public WeChatXmlOutMessage handle(WeChatXmlInMessage inMessage,
                                      WeChatSessionManager sessionManager) {
        return WeChatMessageBuilderFactory.buildText()
            .fromUser(inMessage.getToUserName())
            .toUser(inMessage.getFromUserName())
            .withContent("使用builder生成回复消息")
            .build();
    }
}
