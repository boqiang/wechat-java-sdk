package com.github.congyh.model.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 微信菜单按钮
 *
 * <p>允许且嵌套子菜单</p>
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@Deprecated
public class WeChatButton implements Serializable {
    private static final long serialVersionUID = -8541714101399585158L;
    // 必要字段, 菜单的响应动作类型
    private String type;
    // 必要字段, 菜单标题, 主菜单不超过16字节, 子菜单不超过40字节
    private String name;
    // click等点击类型事件必须的字段.
    // 是菜单的KEY值, 用户消息接口推送, 不超过128字节
    private String key;
    // view类型事件必须, 不超过1024字节
    private String url;
    /*用户点击view_limited类型按钮后，微信客户端将打开开发者在按钮中填写的永久素材id对应的图文消息URL，永久素材类型只支持图文消息。请注意：永久素材id必须是在“素材管理/新增永久素材”接口上传后获得的合法id*/
    private String mediaId;
    private List<WeChatButton> subButtons = new ArrayList<WeChatButton>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public List<WeChatButton> getSubButtons() {
        return subButtons;
    }

    public void setSubButtona(List<WeChatButton> subButtons) {
        this.subButtons = subButtons;
    }
}
