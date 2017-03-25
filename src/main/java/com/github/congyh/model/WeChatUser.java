package com.github.congyh.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 微信用户信息
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class WeChatUser implements Serializable {
    private static final long serialVersionUID = 5326940150278338440L;
    @SerializedName("openid")
    private String openId;
    private String nickname;
    // 1: 男性, 2: 女性, 0: 未知
    private String sex;
    private String province;
    private String city;
    private String country;
    // 用户头像链接
    @SerializedName("headimgurl")
    private String headImgUrl;
    private List<String> privilege;
    @SerializedName("unionid")
    private String unionId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public List<String> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<String> privilege) {
        this.privilege = privilege;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    @Override
    public String toString() {
        return "WeChatUser{" +
            "openId='" + openId + '\'' +
            ", nickname='" + nickname + '\'' +
            ", sex='" + sex + '\'' +
            ", province='" + province + '\'' +
            ", city='" + city + '\'' +
            ", country='" + country + '\'' +
            ", headImgUrl='" + headImgUrl + '\'' +
            ", privilege=" + privilege +
            ", unionId='" + unionId + '\'' +
            '}';
    }
}
