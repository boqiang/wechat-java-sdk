package com.github.congyh.api;

import com.github.congyh.model.WeChatXmlInMessage;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public interface WeChatMessageDuplicateDetectService {

    /**
     * 检测服务器消息是否重复
     *
     * @param inMessage 服务器消息
     * @return true 重复 or false 不重复
     */
    public boolean detectDuplicate(WeChatXmlInMessage inMessage);
}
