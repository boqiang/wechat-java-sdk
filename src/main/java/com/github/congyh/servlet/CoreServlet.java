package com.github.congyh.servlet;

import com.github.congyh.config.AppConfig;
import com.github.congyh.util.VerifyUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.System.out;

/**
 * 请求处理Servlet
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@WebServlet(urlPatterns = "/CoreServlet")
public class CoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        /* 获取微信服务器发来的校验信息*/

        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");

        if (VerifyUtil.checkSignature(signature, timestamp, nonce)) {
            resp.getWriter().print(echostr);
        }
    }
}
