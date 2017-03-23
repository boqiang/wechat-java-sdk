package com.github.congyh.api.impl;

import com.github.congyh.api.WeChatConst;
import com.github.congyh.api.WeChatOAuth2Service;
import com.github.congyh.model.WeChatOAuth2AccessToken;
import com.github.congyh.model.WeChatUser;
import com.github.congyh.util.HttpsUtils;
import com.google.gson.Gson;

import javax.servlet.ServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * OAuth2Service实现类
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class WeChatOAuth2ServiceImpl implements WeChatOAuth2Service {

    @Override
    public String getOAuth2Code(ServletRequest req) {
        return req.getParameter("code");
    }

    @Override
    public WeChatOAuth2AccessToken getOAuth2AccessToken(String code) {
        StringBuilder sb = new StringBuilder();
        sb.append("https://api.weixin.qq.com/sns/oauth2/access_token?")
            .append("appid=").append(WeChatConst.APPID)
            .append("secret=").append(WeChatConst.SECRET)
            .append("code=").append(code)
            .append("grant_type=authorization_code");
        String res = null;
        try {
            res = HttpsUtils.get(sb.toString());
        } catch (NoSuchAlgorithmException | KeyStoreException
            | KeyManagementException | IOException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(res, WeChatOAuth2AccessToken.class);
    }

    @Override
    public WeChatUser getOAuth2UserInfo(WeChatOAuth2AccessToken weChatOAuth2AccessToken) {
        StringBuilder sb = new StringBuilder();
        sb.append("https://api.weixin.qq.com/sns/userinfo?access_token=")
            .append(weChatOAuth2AccessToken.getAccessToken())
            .append("&openid=").append(weChatOAuth2AccessToken.getOpenId())
            .append("&lang=zh_CN");
        String res = null;
        try {
            res = HttpsUtils.get(sb.toString());
        } catch (NoSuchAlgorithmException | KeyStoreException
            | KeyManagementException | IOException e) {
            e.printStackTrace();
        }

        return new Gson().fromJson(res, WeChatUser.class);
    }


    @Override
    public String buildOAuthAuthenticationURL(String redirectURI) {
        StringBuilder sb = new StringBuilder();

        try {
            sb.append("https://open.weixin.qq.com/connect/oauth2/authorize?")
                .append("appid=").append(WeChatConst.APPID)
                .append("&redirect_uri=")
                .append(URLEncoder.encode(redirectURI, "utf-8"))
                .append("&response_type=code")
                .append("&scope=").append(WeChatConst.OAUTH2_SCOPE_USERINFO)
                .append("&state=123#wechat_redirect");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

}
