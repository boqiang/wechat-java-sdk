package com.github.congyh.api.impl;

import com.github.congyh.api.WeChatOAuth2Service;
import org.junit.Before;
import org.junit.Test;

import static java.lang.System.out;
import static org.junit.Assert.*;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class WeChatOAuth2ServiceImplTest {
    private WeChatOAuth2ServiceImpl weChatOAuth2Service;

    @Before
    public void setup() {
        weChatOAuth2Service = new WeChatOAuth2ServiceImpl();
    }

    @Test
    public void buildOAuthAuthenticationURL() throws Exception {
        String url = weChatOAuth2Service.buildOAuthAuthenticationURL();
        out.println(url);
    }

}