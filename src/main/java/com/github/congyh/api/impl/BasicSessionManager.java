package com.github.congyh.api.impl;

import com.github.congyh.api.WeChatSessionManager;
import com.github.congyh.service.session.WeChatSession;
import com.github.congyh.service.session.impl.BasicWeChatSession;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class BasicSessionManager implements WeChatSessionManager, Serializable {
    private static final long serialVersionUID = -2998200255827092319L;
    private final Map<String, WeChatSession> sessionMap = new ConcurrentHashMap<>();

    @Override
    public WeChatSession getSession(String sessionId) {
        if (sessionId == null) {
            return null;
        }
        return sessionMap.get(sessionId);
    }

    @Override
    public WeChatSession getSession(String sessionId, boolean canCreate) {
        if (sessionId == null) {
            return null;
        }

        // 尝试获取已存在的session
        WeChatSession weChatSession = sessionMap.get(sessionId);
        // 如果没有, 且允许创建, 创建并加入sessionMap
        if (weChatSession == null && canCreate) {
            weChatSession = new BasicWeChatSession(sessionId, this);
            sessionMap.put(sessionId, weChatSession);
        }

        return weChatSession;
    }

    @Override
    public void invalidate(WeChatSession weChatSession) {
        if (weChatSession != null)
        sessionMap.remove(weChatSession.getSessionId());
    }
}
