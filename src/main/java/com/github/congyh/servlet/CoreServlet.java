package com.github.congyh.servlet;

import com.github.congyh.model.WeChatXmlInMessage;
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
//        super.doPost(req, resp);
        WeChatXmlInMessage inMessage = getMessage(req);
        String respContent;
        final String msgType = inMessage.getMsgType();
        // TODO 暂时只支持文字和图片两种请求消息的响应
        // 后期可以考虑替换成策略模式
        if (msgType.equals(WeChatConst.REQ_MESSAGE_TYPE_TEXT)) {
            respContent = "您发送的是文字消息";
        } else if (msgType.equals(WeChatConst.REQ_MESSAGE_TYPE_IMAGE)) {
            respContent = "您发送的是图片消息";
        } else {
            respContent = "暂不支持此类消息";
        }
        resp.getWriter().write(respContent);
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
    @Deprecated
    public static String getMsgType(HttpServletRequest req) {
        // TODO
        return null;
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
            .weChatXml2Pojo(xml, WeChatXmlInMessage.class);
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
