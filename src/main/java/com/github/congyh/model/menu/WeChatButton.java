package com.github.congyh.model.menu;

/**
 * 微信菜单按钮
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class WeChatButton {
    // 必要字段, 菜单的响应动作类型
    private String type;
    // 必要字段, 菜单标题, 主菜单不超过16字节, 子菜单不超过40字节
    private String name;
    // click等点击类型事件必须的字段.
    // 是菜单的KEY值, 用户消息接口推送, 不超过128字节
    private String key;
    // view类型事件必须, 不超过1024字节
    private String url;
    // media_id类型和view_limited类型事件必须字段
    // 调用新增永久素材接口返回的合法media_id
    private String mediaId;

}
