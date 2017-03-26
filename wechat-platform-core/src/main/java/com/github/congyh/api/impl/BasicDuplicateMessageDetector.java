package com.github.congyh.api.impl;

import com.github.congyh.api.WeChatDuplicateMessageDetector;
import com.github.congyh.model.WeChatXmlInMessage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 检测服务器消息是否重复
 *
 * <p>注意: 如果需要使用自定义的消息判重器, 务必先停止本消息判重器!
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public final class BasicDuplicateMessageDetector implements WeChatDuplicateMessageDetector{
    // 定时线程池, 用来支持线程工作
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    // 存储当前未过期的消息信息, key为MsgId, value为加入时间
    public final Map<String, Long> messageMap  = new ConcurrentHashMap<>();
    // 不为重复消息的最长时间, 默认为3 * 5s, 由于微信在服务器没有相应的时候每隔5s会重发一次, 一共三次.
    private final long validTime;
    // 清理重复消息的周期, 比validTime小即可, 默认与微信重发时间间隔相同
    private final long clearInterval;

    public BasicDuplicateMessageDetector(long validTime, long clearInterval) {
        this.validTime = validTime;
        this.clearInterval = clearInterval;
        // 不会造成this指针泄漏
        beginScheduledDetect();
    }

    public BasicDuplicateMessageDetector() {
        this(1500L, 500L);
    }

    /**
     * 检测服务器消息是否重复
     *
     * @param inMessage 服务器消息
     * @return true 重复 or false 不重复
     */
    @Override
    public boolean isMessageDuplicate(WeChatXmlInMessage inMessage) {
        String msgId = inMessage.getMsgId().toString();
        // 只要putIfAbsent返回了空, 说明map中之前存在映射, 也就是说消息是重复的.
        Long lastMessageTime = messageMap.putIfAbsent(msgId, System.currentTimeMillis());
        return lastMessageTime != null;
    }

    /**
     * 启动监测线程, 定时清理重复消息
     */
    private void beginScheduledDetect() {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long now = System.currentTimeMillis();
                for (Map.Entry<String, Long> entry: messageMap.entrySet()) {
                    if (now - entry.getValue() >= validTime) { // 消息超时移除
                        messageMap.entrySet().remove(entry);
                    }
                }
            }
        }, 0, clearInterval, TimeUnit.MILLISECONDS);
    }

    /**
     * 停止消息判重器运作
     *
     * <p>如果需要使用自定义的消息判重器, 务必先停止本消息判重器.
     * 如果不进行手动转移, 15秒内的消息不会被判定为重复
     */
    public void endScheduledDetect() {
        executor.shutdownNow();
    }
}
