package com.github.congyh.model;

/**
 * OAuth2.0授权时获取的access_token
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class WeChatOAuth2AccessToken {
    private String accessToken;
    // 过期时间
    private int expiresIn;
    private String refreshToken;
    private String openId;
    private String scope;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "WeChatOAuth2AccessToken{" +
            "accessToken='" + accessToken + '\'' +
            ", expiresIn=" + expiresIn +
            ", refreshToken='" + refreshToken + '\'' +
            ", openId='" + openId + '\'' +
            ", scope='" + scope + '\'' +
            '}';
    }
}
