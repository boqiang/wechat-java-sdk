package com.github.congyh.api.impl;

import com.github.congyh.model.WeChatXmlInMessage;
import com.github.congyh.model.WeChatXmlOutMessage;
import com.github.congyh.api.WeChatMessageHandler;

import java.util.Date;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class SimpleTextHandler extends WeChatMessageHandler {
    @Override
    public WeChatXmlOutMessage handle(WeChatXmlInMessage inMessage) {
        // TODO 后期改用Builder方法构建回复信息
        WeChatXmlOutMessage outMessage =  new WeChatXmlOutMessage(inMessage);
        outMessage.setContent("改版后第一次生效!");
        outMessage.setCreateTime(new Date().getTime());
        return outMessage;
    }
}
