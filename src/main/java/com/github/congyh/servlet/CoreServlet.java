package com.github.congyh.servlet;

import com.github.congyh.model.WeChatXmlInMessage;
import com.github.congyh.model.WeChatXmlOutMessage;
import com.github.congyh.util.VerifyUtils;
import com.github.congyh.util.WeChatConst;
import com.github.congyh.util.XmlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

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
    private class CheckSignatureFailedException extends RuntimeException {

        CheckSignatureFailedException(String message) {
            super(message);
        }

    }

    /**
     * 校验请求

     * <p>确认请求发起者的身份是微信服务器
     *
     * @param req  客户端请求
     * @return true 校验成功 or 抛出 {@code CheckSignatureFailedException}异常
     * @throws ServletException
     * @throws IOException
     */
    private boolean checkSignature(HttpServletRequest req)
        throws ServletException, IOException {
        // 获取微信服务器发来的校验信息

        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");

        if (!VerifyUtils.checkSignature(signature, timestamp, nonce)) {
            throw new CheckSignatureFailedException("签名校验错误");
        }
        return true;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        WeChatXmlInMessage inMessage = getMessage(req);
        WeChatXmlOutMessage outMessage = new WeChatXmlOutMessage(inMessage);
        String respContent;
        final String msgType = inMessage.getMsgType();
        // TODO 在这里添加事件的处理
        if (msgType.equals(WeChatConst.REQ_MESSAGE_TYPE_TEXT)) {
            respContent = "您发送的是文字消息";
        } else if (msgType.equals(WeChatConst.REQ_MESSAGE_TYPE_IMAGE)) {
            respContent = "您发送的是图片消息";
        } else if (msgType.equals(WeChatConst.REQ_MESSAGE_TYPE_EVENT)) {
            String event = inMessage.getEvent();
            String eventKey = inMessage.getEventKey();
            if (event.equals(WeChatConst.EVENT_TYPE_CLICK)) {
                if (eventKey.equals("V1001_GREETINGS")) {
                    respContent = "祝您有美好的一天!";
                } else if (eventKey.equals("V1001_JOIN_US")) {
                    respContent = "请联系微信号: congyihao 讨论详情";
                } else {
                    respContent = "暂不支持此类消息!";
                }
            } else {
                respContent = "暂不支持此类消息!!!";
            }
        } else {
            respContent = "暂不支持此类消息";
        }
        outMessage.setCreateTime(new Date().getTime());
        outMessage.setContent(respContent);
        resp.getWriter().print(XmlUtils.pojo2Xml(outMessage));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        if (checkSignature(req)) {
        // debug
//        if (true) {
            String echostr = req.getParameter("echostr");
            final String reqMethod = req.getMethod();
            // TODO 目前只支持对GET和POST两种方法响应
            if (reqMethod.equals("GET")) {
                resp.getWriter().print(echostr);
            } else if (reqMethod.equals("POST")){
                doPost(req, resp);
            } else {
                throw new UnsupportedOperationException("不支持此种HTTP方法!");
            }
        }
//        super.service(req, resp);
    }

    /**
     * 获取用户请求消息体
     *
     * @param req 用户请求
     * @return 用户请求消息体
     * @throws IOException
     */
    public WeChatXmlInMessage getMessage(HttpServletRequest req)
        throws IOException {
        InputStream in = req.getInputStream();
        String xml = new String(readAllBytes(in));

        return (WeChatXmlInMessage) XmlUtils
            .xml2Pojo(xml, WeChatXmlInMessage.class);
    }

    /**
     * 字节输入流->字节数组
     *
     * @param in 字节输入流
     * @return 字节数组
     */
    public byte[] readAllBytes(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        final int BUFFER_SIZE = 1024;
        byte[] bytes = new byte[BUFFER_SIZE];
        int len = 0;
        while ((len = in.read(bytes)) != -1) {
            out.write(bytes, 0, len);
        }
        out.close();
        // 关闭了之后只是不能再进行写入, 仍可以进行其他操作.
        // ByteArrayOutputStream类型和其他OutputStream一样, 持有一个
        // byte[]类型的buffer, 会随着需要不断增长, toByteArray()操作实际上
        // 是对buffer调用Arrays.copyOf()方法缩减到实际大小
        return out.toByteArray();
    }
}
