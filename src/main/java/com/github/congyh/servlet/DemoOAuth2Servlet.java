package com.github.congyh.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * OAuth2获取登录信息展示样例
 *
 * <pre>
 * 实际使用中, 将{@link com.github.congyh.api.WeChatConst#OAUTU2_REDIRECT_URI OAUTU2_REDIRECT_URI}
 * 替换成自己程序的URI即可
 * </pre>
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@WebServlet("OAuth2Servelt")
public class DemoOAuth2Servlet extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }
}
