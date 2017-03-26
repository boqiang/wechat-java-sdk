package com.github.congyh.api.impl;

import com.github.congyh.api.WeChatSessionManager;
import com.github.congyh.builder.WeChatMessageBuilderFactory;
import com.github.congyh.model.WeChatXmlInMessage;
import com.github.congyh.model.WeChatXmlOutMessage;
import com.github.congyh.api.WeChatMessageHandler;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class SimpleTextHandler extends WeChatMessageHandler {
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
