package com.github.congyh.api.impl;

import com.github.congyh.api.WeChatMessageDuplicateDetectService;
import com.github.congyh.model.WeChatXmlInMessage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 检测服务器消息是否重复
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class WeChatMessageDuplicateDetectServiceImpl implements WeChatMessageDuplicateDetectService {
    public static final WeChatMessageDuplicateDetectServiceImpl INSTANCE;

    static {
        // TODO 目前还不支持自定制, 需要添加set方法, 能够在取得实例之后进行使用.
        INSTANCE = new WeChatMessageDuplicateDetectServiceImpl(1500L, 500L);
        INSTANCE.executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long now = System.currentTimeMillis();
                for (Map.Entry<String, Long> entry: INSTANCE.messageMap.entrySet()) {
                    if (now - entry.getValue() >= INSTANCE.validTime) { // 消息超时移除
                        INSTANCE.messageMap.entrySet().remove(entry);
                    }
                }
            }
        }, 0, INSTANCE.clearInterval, TimeUnit.MILLISECONDS);
    }
    // 定时线程池, 用来支持线程工作
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    // 存储当前未过期的消息信息, key为MsgId, value为加入时间
    public final ConcurrentHashMap<String, Long> messageMap = new ConcurrentHashMap<>();
    // 不为重复消息的最长时间, 默认为3 * 5s, 由于微信在服务器没有相应的时候每隔5s会重发一次, 一共三次.
    private long validTime;
    // 清理重复消息的周期, 比validTime小即可, 默认与微信重发时间间隔相同
    private long clearInterval;

    private WeChatMessageDuplicateDetectServiceImpl(long validTime, long clearInterval) {
        this.validTime = validTime;
        this.clearInterval = clearInterval;
    }

    public static WeChatMessageDuplicateDetectService getService() {
        return INSTANCE;
    }
    @Override
    public boolean detectDuplicate(WeChatXmlInMessage inMessage) {
        String msgId = inMessage.getMsgId().toString();
        // 只要putIfAbsent返回了空, 说明map中之前存在映射, 也就是说消息是重复的.
        Long lastMessageTime = this.messageMap.putIfAbsent(msgId, System.currentTimeMillis());
        return lastMessageTime != null;
    }
}
