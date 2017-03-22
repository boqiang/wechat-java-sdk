package com.github.congyh.service.impl;

import com.github.congyh.exception.CheckSignatureFailedException;
import com.github.congyh.model.WeChatXmlInMessage;
import com.github.congyh.model.WeChatXmlOutMessage;
import com.github.congyh.service.WeChatMessageHandler;
import com.github.congyh.service.WeChatService;
import com.github.congyh.util.VerifyUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 微信公众平台后台中控Service
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class WeChatServiceImpl implements WeChatService {
//    private final ExecutorService executorService = Executors.newCachedThreadPool();
    @Override
    public WeChatXmlOutMessage handleMessage(WeChatXmlInMessage inMessage,
                                      WeChatMessageHandler handler) {
        // 由于Servlet处理消息本来就是多线程的, 所以这里就不进行多线程处理了
//        if (handler.getRule().isAsync()) {
//            Future<WeChatXmlOutMessage> res = executorService.submit(new Callable<WeChatXmlOutMessage>() {
//                @Override
//                public WeChatXmlOutMessage call() throws Exception {
//                    return handler.handle(inMessage);
//                }
//            });
//            // TODO 这样紧接着就取结果的做法和同步处理没有任何差别, 如何改进?
//            try {
//                return res.get();
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//                return null;
//            }
//        } else {
//            return handler.handle(inMessage);
//        }

        return handler.handle(inMessage);
    }


    @Override
    public boolean checkSignature(HttpServletRequest req)
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
}
