package com.github.congyh.api.impl;

import com.github.congyh.api.WeChatConst;
import com.github.congyh.api.WeChatMessageHandler;
import com.github.congyh.api.WeChatOAuth2Service;
import com.github.congyh.model.WeChatMessageBuilderFactory;
import com.github.congyh.model.WeChatXmlInMessage;
import com.github.congyh.model.WeChatXmlOutMessage;

/**
 * 测试发起OAuth2授权请求
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class DemoOAuth2Handler extends WeChatMessageHandler {
    // TODO 这里还需要改造, 初定与WeChatService结合?
    private final WeChatOAuth2Service weChatOAuth2Service = new WeChatOAuth2ServiceImpl();

    @Override
    public WeChatXmlOutMessage handle(WeChatXmlInMessage inMessage) {
        // 构造要发送给用户的URL
        String url = weChatOAuth2Service.buildOAuthAuthenticationURL(
            WeChatConst.OAUTU2_REDIRECT_URI + "/OAuth2Servlet");
        return WeChatMessageBuilderFactory.buildText()
            .fromUser(inMessage.getToUserName())
            .toUser(inMessage.getFromUserName())
            .withContent(url)
            .build();
    }
}
