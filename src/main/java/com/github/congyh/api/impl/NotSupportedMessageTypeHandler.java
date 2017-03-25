package com.github.congyh.api.impl;

import com.github.congyh.api.WeChatMessageHandler;
import com.github.congyh.model.WeChatMessageBuilderFactory;
import com.github.congyh.model.WeChatXmlInMessage;
import com.github.congyh.model.WeChatXmlOutMessage;

/**
 * 当没有符合的规则时, 自动使用此处理器进行处理
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class NotSupportedMessageTypeHandler extends WeChatMessageHandler {
    @Override
    public WeChatXmlOutMessage handle(WeChatXmlInMessage inMessage) {
        return WeChatMessageBuilderFactory.buildText()
            .fromUser(inMessage.getToUserName())
            .toUser(inMessage.getFromUserName())
            .withContent("很抱歉, 我不知道如何处理您的消息T-T")
            .build();
    }
}
