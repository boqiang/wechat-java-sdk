package com.github.congyh.model.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 微信菜单
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@Deprecated
public class WeChatMenu implements Serializable {
    private static final long serialVersionUID = 5719131927869249915L;
    private List<WeChatButton> buttons = new ArrayList<>();

    public List<WeChatButton> getButtons() {
        return buttons;
    }

    public void setButtons(List<WeChatButton> buttons) {
        this.buttons = buttons;
    }
}
