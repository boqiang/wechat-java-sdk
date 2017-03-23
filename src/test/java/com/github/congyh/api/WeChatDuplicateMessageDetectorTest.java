package com.github.congyh.api;

import com.github.congyh.model.WeChatXmlInMessage;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class WeChatDuplicateMessageDetectorTest {

    @Test
    public void testDetectDuplicate() throws InterruptedException {

        WeChatXmlInMessage inMessage0 = new WeChatXmlInMessage();
        WeChatXmlInMessage inMessage1 = new WeChatXmlInMessage();
        WeChatXmlInMessage inMessage2 = new WeChatXmlInMessage();
        WeChatXmlInMessage inMessage3 = new WeChatXmlInMessage();
        WeChatXmlInMessage inMessage4 = new WeChatXmlInMessage();
        WeChatXmlInMessage inMessage5 = new WeChatXmlInMessage();
        inMessage0.setMsgId(1000L);
        inMessage1.setMsgId(1001L);
        inMessage2.setMsgId(1002L);
        inMessage3.setMsgId(1003L);
        inMessage4.setMsgId(1004L);
        inMessage5.setMsgId(1005L);
        List<WeChatXmlInMessage> list = new LinkedList<>();
        list.add(inMessage0);
        list.add(inMessage1);
        list.add(inMessage2);
        list.add(inMessage3);
        list.add(inMessage4);
        list.add(inMessage5);

        for (WeChatXmlInMessage inMessage: list) {
            assertFalse(WeChatDuplicateMessageDetector.detectDuplicate(inMessage));
        }

        // 1秒后检测, 应该判定为重复消息
        Thread.sleep(1000L);
        for (WeChatXmlInMessage inMessage: list) {
            assertTrue(WeChatDuplicateMessageDetector.detectDuplicate(inMessage));
        }

        //再过1秒后检测, 此时应该作为新消息
        Thread.sleep(1000L);
        for (WeChatXmlInMessage inMessage: list) {
            assertFalse(WeChatDuplicateMessageDetector.detectDuplicate(inMessage));
        }
    }
}