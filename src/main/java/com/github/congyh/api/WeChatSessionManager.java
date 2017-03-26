package com.github.congyh.api;

import com.github.congyh.service.session.WeChatSession;
import com.github.congyh.service.session.impl.BasicWeChatSession;

/**
 * 定义session管理的基本方法
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public interface WeChatSessionManager {

    /**
     * 根据sessionId获取session
     *
     * <p>如果存在, 则直接返回, 不存在新建
     *
     * @param sessionId session对象唯一标识符
     * @return session对象
     */
    public WeChatSession getSession(String sessionId);

    /**
     * 根据sessionId获取session
     *
     * <p>如果存在直接返回, 如果不存在, 根据{@code canCreate}决定是否创建</p>
     *
     * @param sessionId session对象唯一标识符
     * @param canCreate 是否可以创建新session对象
     * @return session对象 or null
     */
    public WeChatSession getSession(String sessionId, boolean canCreate);

    /**
     * 使Session对象立即失效
     *
     * @param weChatSession session对象
     */
    public void invalidate(WeChatSession weChatSession);
}
