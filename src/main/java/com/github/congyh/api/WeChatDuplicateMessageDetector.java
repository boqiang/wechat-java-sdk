package com.github.congyh.api;

import com.github.congyh.model.WeChatXmlInMessage;

/**
 * 服务器消息排重器
 *
 * <pre>
 * 对于微信服务器转发来的消息, 如果web应用不能在5s内给予响应,
 * 服务器会重复发送, 一共三次. 如果之前的消息并不是由于丢包导致未响应,
 * 则很有可能造成同一消息重复处理, 所以需要对服务器消息进行排重.
 * </pre>
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public interface WeChatDuplicateMessageDetector {

    /**
     * 检验服务器消息是否重复
     *
     * @param inMessage 服务器消息
     * @return true 如果判定为重复消息 or false 如果是新消息
     */
    public boolean isMessageDuplicate(WeChatXmlInMessage inMessage);
}
