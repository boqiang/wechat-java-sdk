package com.github.congyh.api.impl;

import com.github.congyh.api.WeChatSessionManager;
import com.github.congyh.service.session.WeChatSession;
import com.github.congyh.service.session.impl.BasicWeChatSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SessionManager的简单实现
 *
 * <p>如有需要, 可以实现自己的SessionManager, 但无论如何, 应该是单例的.</p>
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class BasicSessionManager implements WeChatSessionManager {
    private final Map<String, WeChatSession> sessionMap = new ConcurrentHashMap<>();
    private long sessionTTL;

    public BasicSessionManager() {
        // 默认session存活时间为一小时
        this(60 * 60 * 1000L);
    }

    public BasicSessionManager(long sessionTTL) {
        this.sessionTTL = sessionTTL;
    }

    @Override
    public WeChatSession getSession(String sessionId) {
        if (sessionId == null) {
            return null;
        }
        WeChatSession weChatSession = sessionMap.get(sessionId);
        if (weChatSession == null) { // 不存在直接创建
            return new BasicWeChatSession(sessionId, this);
        }
        // 若是session超时, 新建并替换原session
        if (System.currentTimeMillis() - ((BasicWeChatSession) weChatSession).getCreateTime() >= sessionTTL ) {
            sessionMap.replace(sessionId, new BasicWeChatSession(sessionId, this));
        }

        // 存在且没超时
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

    public void setSessionTTL(long sessionTTL) {
        this.sessionTTL = sessionTTL;
    }
}
