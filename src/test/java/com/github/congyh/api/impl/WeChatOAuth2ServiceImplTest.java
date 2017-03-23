package com.github.congyh.api.impl;

import com.github.congyh.api.WeChatConst;
import org.junit.Before;
import org.junit.Test;

import static java.lang.System.out;

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
        String url = weChatOAuth2Service.buildOAuthAuthenticationURL(
            WeChatConst.OAUTH2_REDIRECT_URI + "/OAuth2Servlet");
        out.println(url);
    }

    @Test
    public void getOAuth2AccessToken() {
//        weChatOAuth2Service.getOAuth2AccessToken("")
    }

}