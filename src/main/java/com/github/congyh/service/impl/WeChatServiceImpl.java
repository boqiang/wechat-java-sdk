package com.github.congyh.service.impl;

import com.github.congyh.model.WeChatXmlInMessage;
import com.github.congyh.model.WeChatXmlOutMessage;
import com.github.congyh.service.WeChatMessageHandler;
import com.github.congyh.service.WeChatService;

import java.util.concurrent.*;

/**
 * 微信公众平台后台中控Service
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class WeChatServiceImpl implements WeChatService {
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    @Override
    public WeChatXmlOutMessage handle(WeChatXmlInMessage inMessage,
                                      WeChatMessageHandler handler) {
        if (handler.getRule().isAsync()) {
            Future<WeChatXmlOutMessage> res = executorService.submit(new Callable<WeChatXmlOutMessage>() {
                @Override
                public WeChatXmlOutMessage call() throws Exception {
                    return handler.handle(inMessage);
                }
            });
            // TODO 这样紧接着就取结果的做法和同步处理没有任何差别, 如何改进?
            try {
                return res.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return handler.handle(inMessage);
        }
    }
}
