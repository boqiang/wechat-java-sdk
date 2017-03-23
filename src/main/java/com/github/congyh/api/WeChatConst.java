package com.github.congyh.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 微信公众平台常量汇总, 包括以下分类:
 * <ul>
 *     <li>服务器消息类型</li>
 *     <li>用户事件类型</li>
 *     <li>回复消息类型</li>
 *     <li>配置项(加载自config.properties)</li>
 * </ul>
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public final class WeChatConst {
    // config.properties中定义的配置项
    private static final Properties properties = new Properties();

    static {
        loadConfig();
    }

    private WeChatConst() {}

    /*定义用户请求消息类型*/

    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    public static final String REQ_MESSAGE_TYPE_LINK = "link";
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /*定义用户事件类型*/

    // 订阅与取消订阅
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    public static final String EVENT_TYPE_SCAN = "scan";
    public static final String EVENT_TYPE_LOCATION = "location";
    public static final String EVENT_TYPE_CLICK = "CLICK";
    public static final String EVENT_TYPE_VIEW = "VIEW";

    /*定义服务器响应消息类型*/

    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
    public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";

    // 获取access_token的url前缀
    public static final String ACCESS_TOKEN_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/token?";
    public static final String MENU_CREATE_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
    public static final String TOKEN = properties.getProperty("token");
    public static final String APPID = properties.getProperty("appid");
    public static final String SECRET = properties.getProperty("secret");

    public static final String OAUTU2_REDIRECT_URI = "http://congyh.duapp.com";
    // 使用此scope, 无需用户点击同意授权, 但只能获取到用户的OpenId
    public static final String OAUTH2_SCOPE_BASE = "snsapi_base";
    // 使用此scope, 需要用户点击同意授权, 可以获取到用户的OpenID, 用户昵称, 性别, 所在城市, 用户头像等信息
    public static final String OAUTH2_SCOPE_USERINFO = "snsapi_userinfo";



    private static void loadConfig() {
        try (InputStream in = WeChatConst.class.getResourceAsStream("/config.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
