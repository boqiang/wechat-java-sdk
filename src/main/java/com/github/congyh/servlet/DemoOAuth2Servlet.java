package com.github.congyh.servlet;

import com.github.congyh.api.WeChatConst;
import com.github.congyh.api.WeChatOAuth2Service;
import com.github.congyh.api.impl.WeChatOAuth2ServiceImpl;
import com.github.congyh.model.WeChatOAuth2AccessToken;
import com.github.congyh.model.WeChatUser;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * OAuth2.0样例
 *
 * <pre>
 * {@link WeChatConst#OAUTH2_REDIRECT_URI OAUTH2_REDIRECT_URI}
 *
 * </pre>
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@WebServlet("/OAuth2Servlet")
public class DemoOAuth2Servlet extends HttpServlet {
    // TODO 这里还没有处理好WeChatOAuth2Service的单例, 注入问题, 因为除了这里还有其他地方要用到
    // 这个Service, 而且Servlet最好是无状态的, 这里没有协调好
    private final WeChatOAuth2Service weChatOAuth2Service = new WeChatOAuth2ServiceImpl();
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=utf-8");
        // 获取授权码
        // TODO 这里后期应该将这几步合成一个连续的操作, 放在service里
        String code = weChatOAuth2Service.getOAuth2Code(req);
        WeChatOAuth2AccessToken accessToken = weChatOAuth2Service.getOAuth2AccessToken(code);
        WeChatUser user = weChatOAuth2Service.getOAuth2UserInfo(accessToken);

        PrintWriter out = res.getWriter();
        out.println("<h2>code</h2>");
        out.println(code);
        out.println("<h2>access_token</h2>");
        out.println(accessToken.toString());
        out.println("<h2>userinfo</h2>");
        out.println(user.toString());

        out.flush();
        out.close();
    }
}
