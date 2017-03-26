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
    private String sessionId;
    private long createTime;
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
        this.createTime = System.currentTimeMillis();
    }

    @Override
    public Object getAttribute(String name) {
        if (name == null) {
            return null;
        }
        // 单步get, 无需手动控制同步
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
            return;
        }
        // 只能保证最终一致性
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

    public long getCreateTime() {
        return createTime;
    }
}
