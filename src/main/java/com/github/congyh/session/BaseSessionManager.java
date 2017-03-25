package com.github.congyh.session;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class BaseSessionManager implements WeChatSessionManager {
    @Override
    public WeChatSession getSession(String sessionId) {
        return null;
    }

    @Override
    public WeChatSession getSession(String sessionId, boolean canCreate) {
        return null;
    }
}
