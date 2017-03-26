package com.github.congyh.api;

import com.github.congyh.model.WeChatOAuth2AccessToken;
import com.github.congyh.model.WeChatUser;

import javax.servlet.ServletRequest;

/**
 * OAuth2.0授权服务
 *
 * <pre>
 * 为什么要做OAuth2.0网页授权呢? 因为用户通过点击自定义菜单的view按钮, 跳转的网页无法识别用户的身份,
 * 通过OAuth2.授权, 能够获取到访问公众账号的用户信息: 包括OpenID, 用户昵称, 性别, 所在城市, 用户头像等.
 *
 * 进行OAuth2.0授权服务的步骤:
 * 1) 引导用户进入授权页面, 用户同意授权后, 开发者能够获取到code;
 * 2) 通过code换取网页授权access_token, 有效期7200s, 可以手动刷新, 避免过期;
 * 3) 通过网页授权access_token和OpenID获取用户基本信息.
 *
 * 需要在微信公众平台后台开启"网页授权获取用户基本信息"权限, 并修改授权回调页面域名设置
 *
 * 参考链接<a href="https://mp.weixin.qq.com/wiki/4/9ac2e7b1f1d22e9e57260f6553822520.html">
 *     网页授权获取用户信息</a>
 * </pre>
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public interface WeChatOAuth2Service {
    /**
     * 获取OAuth2.0授权码
     *
     * @param req 请求
     * @return OAuth2.0授权码
     */
    public String getOAuth2Code(ServletRequest req);

    /**
     * 获取OAuth2.0授权access_token
     *
     * @param code OAuth2.0授权码
     * @return OAuth2.0 access_token
     */
    public WeChatOAuth2AccessToken getOAuth2AccessToken(String code);

    /**
     * 获取通过OAuth2.0授权的微信用户信息
     *
     * @param weChatOAuth2AccessToken OAuth2AccessToken
     * @return 微信用户信息
     */
    public WeChatUser getOAuth2UserInfo(WeChatOAuth2AccessToken weChatOAuth2AccessToken);

    /**
     * 构造OAuth2.0授权页面URL
     *
     * @param redirectURI 自定义的重定向URI
     * @return 授权页面URL
     */
    String buildOAuthAuthenticationURL(String redirectURI);
}
