package com.github.congyh.api;

import com.github.congyh.model.WeChatXmlInMessage;
import com.github.congyh.model.WeChatXmlOutMessage;

/**
 * 服务器消息处理器
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public interface WeChatMessageHandler {
    /**
     * 负责具体处理服务器消息
     *
     * @return 回复消息
     */
    public WeChatXmlOutMessage handle(WeChatXmlInMessage inMessage);
}
