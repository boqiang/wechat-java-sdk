package com.github.congyh.servlet;

import com.github.congyh.util.VerifyUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求处理Servlet
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@WebServlet(urlPatterns = "/CoreServlet")
public class CoreServlet extends HttpServlet {

    /**
     * 校验签名失败异常
     *
     * <p>非受检异常
     */
    private class checkSignatureFailedException extends RuntimeException {

        checkSignatureFailedException(String message) {
            super(message);
        }

    }

    /**
     * 校验请求

     * <p>确认请求发起者的身份是微信服务器
     *
     * @param req  客户端请求
     * @param resp 服务器端响应
     * @throws ServletException
     * @throws IOException
     */
    private void checkSignature(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // 获取微信服务器发来的校验信息

        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");

        if (!VerifyUtils.checkSignature(signature, timestamp, nonce)) {
            throw new checkSignatureFailedException("签名校验错误");
        }
        resp.getWriter().print(echostr);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        checkSignature(req, resp);
        super.service(req, resp);
    }

    /**
     * 获取用户请求消息类型
     *
     * @param req 用户请求
     * @return 用户请求消息类型
     */
    public static String getMsgType(HttpServletRequest req) {
        // TODO
        return null;
    }
}
