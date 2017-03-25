package com.github.congyh.servlet;

import com.github.congyh.api.WeChatMessageRouter;
import com.github.congyh.model.WeChatXmlInMessage;
import com.github.congyh.model.WeChatXmlOutMessage;
import com.github.congyh.api.WeChatMessageHandler;
import com.github.congyh.api.WeChatService;
import com.github.congyh.api.impl.WeChatServiceImpl;
import com.github.congyh.util.XmlUtils;
import org.apache.commons.lang.StringUtils;

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
public class CoreServlet extends HttpServlet {

    private final WeChatService weChatService = new WeChatServiceImpl();

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
//        WeChatXmlOutMessage outMessage = new WeChatXmlOutMessage(inMessage);
//        String respContent;
//        final String msgType = inMessage.getMsgType();
//        if (msgType.equals(WeChatConst.REQ_MESSAGE_TYPE_TEXT)) {
//            respContent = "您发送的是文字消息";
//        } else if (msgType.equals(WeChatConst.REQ_MESSAGE_TYPE_IMAGE)) {
//            respContent = "您发送的是图片消息";
//        } else if (msgType.equals(WeChatConst.REQ_MESSAGE_TYPE_EVENT)) {
//            String event = inMessage.getEvent();
//            String eventKey = inMessage.getEventKey();
//            if (event.equals(WeChatConst.EVENT_TYPE_CLICK)) {
//                if (eventKey.equals("V1001_GREETINGS")) {
//                    respContent = "祝您有美好的一天!";
//                } else if (eventKey.equals("V1001_JOIN_US")) {
//                    respContent = "请联系微信号: congyihao 讨论详情";
//                } else {
//                    respContent = "暂不支持此类消息!";
//                }
//            } else {
//                respContent = "暂不支持此类消息!!!" + event;
//            }
//        } else {
//            respContent = "暂不支持此类消息";
//        }
//        outMessage.setCreateTime(new Date().getTime());
//        outMessage.setContent(respContent);
//        resp.getWriter().print(XmlUtils.pojo2Xml(outMessage));

        // 路由到合适的handler
        WeChatMessageHandler handler = WeChatMessageRouter.route(inMessage);
        if (handler == null) {
            return;
        }
        WeChatXmlOutMessage outMessage = this.weChatService
            .handleMessage(inMessage, handler);
        resp.getWriter().print(XmlUtils.pojo2Xml(outMessage));
    }


//    /**
//     * 获取用户请求消息体
//     *
//     * @param req 用户请求
//     * @return 用户请求消息体
//     * @throws IOException
//     */
//    public WeChatXmlInMessage getMessage(HttpServletRequest req)
//        throws IOException {
//        InputStream in = req.getInputStream();
//        String xml = new String(readAllBytes(in));
//
//        return XmlUtils.xml2Pojo(xml, WeChatXmlInMessage.class);
//    }


}
