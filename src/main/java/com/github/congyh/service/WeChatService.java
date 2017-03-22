package com.github.congyh.service;

import com.github.congyh.model.WeChatXmlInMessage;
import com.github.congyh.model.WeChatXmlOutMessage;

/**
 * 汇总微信公众平台所有能提供的服务, 属于中控的service
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public interface WeChatService {
    /**
     * 处理服务器消息
     *
     * @return 回复消息
     */
    public WeChatXmlOutMessage handle(WeChatXmlInMessage inMessage,
                                      WeChatMessageHandler handler);
}
