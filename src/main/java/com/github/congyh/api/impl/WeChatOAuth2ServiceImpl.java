package com.github.congyh.api.impl;

import com.github.congyh.api.WeChatConst;
import com.github.congyh.api.WeChatOAuth2Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * OAuth2Service实现类
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class WeChatOAuth2ServiceImpl implements WeChatOAuth2Service {

    @Override
    public String getOAuth2Code() {
        return null;
    }

    /**
     * 构造OAuth2.0授权页面URL
     *
     * @return 授权页面URL
     */
    public String buildOAuthAuthenticationURL() {
        StringBuilder sb = new StringBuilder();

        try {
            sb.append("https://open.weixin.qq.com/connect/oauth2/authorize?")
                .append("appid=").append(WeChatConst.APPID)
                .append("&redirect_uri=")
                .append(URLEncoder.encode(WeChatConst.OAUTU2_REDIRECT_URI, "utf-8"))
                .append("&response_type=code")
                .append("&scope=").append(WeChatConst.OAUTH2_SCOPE_USERINFO)
                .append("&state=123#wechat_redirect");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

}
