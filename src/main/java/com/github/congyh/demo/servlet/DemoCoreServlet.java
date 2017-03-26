package com.github.congyh.demo.servlet;

import com.github.congyh.api.WeChatConst;
import com.github.congyh.demo.api.impl.DemoOAuth2Handler;
import com.github.congyh.demo.api.impl.DemoSessionHandler;
import com.github.congyh.service.WeChatMessageRouter;
import com.github.congyh.api.WeChatService;
import com.github.congyh.api.impl.WeChatServiceImpl;
import com.github.congyh.model.WeChatXmlInMessage;
import com.github.congyh.model.WeChatXmlOutMessage;
import com.github.congyh.util.HttpUtils;
import com.github.congyh.util.XmlUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微信公众平台中控Servlet, 整个程序的入口
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@WebServlet(urlPatterns = "/CoreServlet")
public class DemoCoreServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DemoCoreServlet.class);
    private static final long serialVersionUID = -2002712829315094131L;
    private final WeChatService weChatService = new WeChatServiceImpl();

    static {
        WeChatMessageRouter.addRule().withMsgType(WeChatConst.REQ_MESSAGE_TYPE_TEXT)
            .withContent("OAuth测试")
            .useHandler(new DemoOAuth2Handler())
            .endRule();
        WeChatMessageRouter.addRule().withMsgType(WeChatConst.REQ_MESSAGE_TYPE_TEXT)
            .useHandler(new DemoSessionHandler())
            .endRule();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        // TODO 这里应该判定如果同一个session检测一次即可
        // 如果校验不成功, 说明是非微信服务器消息, 返回警告信息
        if (!this.weChatService.checkSignature(req)) {
            resp.getWriter().print("警告: 请立即停止非法行为!");
            return;
        }

        String echoStr = req.getParameter("echostr");
        // 如果请求中这个字段有值, 那么就是一个简单的"GET"类型的验证请求,
        if (StringUtils.isNotBlank(echoStr)) {
            resp.getWriter().print(echoStr);
            return;
        }

        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        WeChatXmlInMessage inMessage = XmlUtils.xml2Pojo(
            req.getInputStream(), WeChatXmlInMessage.class);

        // 路由并处理消息
        WeChatXmlOutMessage outMessage = WeChatMessageRouter.route(inMessage);
        logger.debug(HttpUtils.getRequestHeaderAsString(req));
        logger.debug(outMessage.getContent());
        resp.getWriter().print(XmlUtils.pojo2Xml(outMessage));
    }
}
