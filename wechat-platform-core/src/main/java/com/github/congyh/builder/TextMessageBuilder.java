package com.github.congyh.builder;

import com.github.congyh.api.WeChatConst;
import com.github.congyh.model.WeChatXmlOutMessage;

/**
 * 文本消息生成器
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class TextMessageBuilder extends AbstractMessageBuilder<TextMessageBuilder, WeChatXmlOutMessage> {
    private String content;

    public TextMessageBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public WeChatXmlOutMessage build() {
        WeChatXmlOutMessage outMessage = new WeChatXmlOutMessage();
        // 设置通用部分
        preBuild(outMessage);
        outMessage.setContent(this.content);
        outMessage.setMsgType(WeChatConst.REQ_MESSAGE_TYPE_TEXT);
        return outMessage;
    }
}
