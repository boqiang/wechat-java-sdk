package com.github.congyh.model.menu;

import com.google.gson.annotations.SerializedName;

/**
 * 菜单, 由按钮组成
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class Menu {
    @SerializedName("button")
    private Button[] buttons;

    public Button[] getButtons() {
        return buttons;
    }

    public void setButtons(Button[] buttons) {
        this.buttons = buttons;
    }
}
