package com.github.congyh.util;

import com.github.congyh.api.WeChatConst;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class WeChatConstTest {

    @Test
    public void loadConfig() {
        assertEquals("asgsgsdgsdf", WeChatConst.TOKEN);
    }
}