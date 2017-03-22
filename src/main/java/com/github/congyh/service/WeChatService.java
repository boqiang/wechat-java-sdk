package com.github.congyh.service;

import com.github.congyh.model.WeChatXmlInMessage;
import com.github.congyh.model.WeChatXmlOutMessage;

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
     * 处理服务器消息
     *
     * @return 回复消息
     */
    public WeChatXmlOutMessage handleMessage(WeChatXmlInMessage inMessage,
                                      WeChatMessageHandler handler);

    /**
     * 校验请求

     * <p>确认请求发起者的身份是微信服务器
     *
     * @param req  客户端请求
     * @return true 校验成功 or 抛出 {@code CheckSignatureFailedException}异常
     * @throws ServletException
     * @throws IOException
     */
    boolean checkSignature(HttpServletRequest req)
        throws ServletException, IOException;
}
