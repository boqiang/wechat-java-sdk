package com.github.congyh.service.session.impl;

import com.github.congyh.api.WeChatSessionManager;
import com.github.congyh.model.WeChatXmlInMessage;
import com.github.congyh.service.session.WeChatSession;

import java.io.Serializable;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Session对象的简单实现
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class BasicWeChatSession implements WeChatSession, Serializable {
    private static final long serialVersionUID = -3027813728840717369L;
    private final WeChatSessionManager sessionManager;
    // TODO 需要一个session id
    // TODO WeChatSession需要有一个能够操作Id的方法
    private String sessionId;
    private Map<String, Object> attributesMap = new ConcurrentHashMap<>();

    /**
     * 必须手动指定Session id
     *
     * @param sessionId 手动生成的sessionId, 可以考虑使用{@link WeChatXmlInMessage#getFromUserName() fromUserName}
     *                  来作为SessionId
     * @param sessionManager 管理session实体的manager, 负责session对象生命周期的管控
     */
    public BasicWeChatSession(String sessionId, WeChatSessionManager sessionManager) {
        this.sessionId = sessionId;
        this.sessionManager = sessionManager;
    }

    @Override
    public Object getAttribute(String name) {
        if (name == null) {
            return null;
        }
        return attributesMap.get(name);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return Collections.enumeration(attributesMap.keySet());
    }

    @Override
    public void setAttribute(String name, Object value) {
        if (name == null) {
            return;
        }
        if (value == null) {
            removeAttribute(name);
        }
        attributesMap.put(name, value);
    }

    @Override
    public void removeAttribute(String name) {
        if (name == null) {
            return;
        }
        attributesMap.remove(name);
    }

    @Override
    public void invalidate() {
        sessionManager.invalidate(this);
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
