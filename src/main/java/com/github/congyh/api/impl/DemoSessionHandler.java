package com.github.congyh.api.impl;

import com.github.congyh.api.WeChatMessageHandler;
import com.github.congyh.api.WeChatSessionManager;
import com.github.congyh.builder.WeChatMessageBuilderFactory;
import com.github.congyh.model.WeChatXmlInMessage;
import com.github.congyh.model.WeChatXmlOutMessage;
import com.github.congyh.service.session.impl.BasicWeChatSession;

/**
 * 展示session作用
 *
 * <pre>
 * 使用方法: 在公众号发送任意文字即可发起测试.
 * </pre>
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class DemoSessionHandler extends WeChatMessageHandler {
    @Override
    public WeChatXmlOutMessage handle(WeChatXmlInMessage inMessage, WeChatSessionManager sessionManager) {
        BasicWeChatSession weChatSession =
            (BasicWeChatSession) sessionManager.getSession(inMessage.getFromUserName());
        String formerContent = (String) weChatSession.getAttribute("formerContent");
        if (formerContent != null) {
            weChatSession.setAttribute("formerContent", inMessage.getContent());
            return WeChatMessageBuilderFactory.buildText()
                .fromUser(inMessage.getToUserName())
                .toUser(inMessage.getFromUserName())
                .withContent("上一条内容是: " + formerContent)
                .build();
        } else {
            weChatSession.setAttribute("formerContent", inMessage.getContent());
            return WeChatMessageBuilderFactory.buildText()
                .fromUser(inMessage.getToUserName())
                .toUser(inMessage.getFromUserName())
                .withContent("这是您发送的第一条消息!")
                .build();
        }
    }
}
