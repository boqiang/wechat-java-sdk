package com.github.congyh.model;

import com.google.gson.Gson;
import org.junit.Test;

import static java.lang.System.out;
import static org.junit.Assert.*;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class WeChatOAuth2AccessTokenTest {
    @Test
    public void toJson() {
        WeChatOAuth2AccessToken accessToken = new WeChatOAuth2AccessToken();
        accessToken.setAccessToken("accesstoken");
        out.println(new Gson().toJson(accessToken));
    }

    @Test
    public void fromJson() {
        out.println(new Gson().fromJson("{\"access_token\":\"accesstoken\",\"expires_in\":0}",
            WeChatOAuth2AccessToken.class));
    }
}