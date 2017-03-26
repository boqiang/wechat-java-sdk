package com.github.congyh.service.session;

import java.util.Enumeration;

/**
 * 定义session的行为
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public interface WeChatSession {

    /**
     * 按名获取属性
     *
     * @param name 属性名
     * @return 属性值
     */
    public Object getAttribute(String name);

    /**
     * 获取所有的属性名
     *
     * <p>使用Enumeration类的目的是与{@link javax.servlet.http.HttpSession}
     * 的用法保持一致</p>
     *
     * @return 属性名"迭代器"
     */
    public Enumeration<String> getAttributeNames();

    public void setAttribute(String name, Object value);

    public void removeAttribute(String name);

    /**
     * 强制使session失效
     */
    public void invalidate();

    /**
     * 获取sessionId
     *
     * <p>用于Session Manager根据Session Id来获取Session对象</p>
     *
     * @return sessionId
     */
    public String getSessionId();
}
