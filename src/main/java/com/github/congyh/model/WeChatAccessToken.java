package com.github.congyh.model;

/**
 * 微信公众平台的API所需的access_token实体
 *
 * <p><strong>WARNING</strong> 为了和Java命名规范保持一致, 没有选择直接使用公众平台json中提供的字段名
 * access_token和expires_in, 需要自己进行手动处理.
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class WeChatAccessToken {
    private String accessToken;
    private int expiresIn;

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
}
