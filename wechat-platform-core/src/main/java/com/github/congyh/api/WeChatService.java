package com.github.congyh.api;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 汇总微信公众平台所有能提供的服务, 属于中控的service
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public interface WeChatService {

    /**
     * 校验请求

     * <p>确认请求发起者的身份是微信服务器
     *
     * @param req  客户端请求
     * @return true 校验成功 or false 失败
     * @throws ServletException
     * @throws IOException
     */
    public boolean checkSignature(HttpServletRequest req)
        throws ServletException, IOException;
}
