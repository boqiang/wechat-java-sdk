package com.github.congyh.api.impl;

import com.github.congyh.api.WeChatSessionManager;
import com.github.congyh.model.session.WeChatSession;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class BasicSessionManager implements WeChatSessionManager {
    // TODO 需要一个存储session对象的数据结构, 初定为ConcurrentHashMap??
    @Override
    public WeChatSession getSession(String sessionId) {
        return null;
    }

    @Override
    public WeChatSession getSession(String sessionId, boolean canCreate) {
        return null;
    }
}
