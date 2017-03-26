package com.github.congyh.model.menu;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 带子按钮的按钮
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class ButtonWithSubbuttons extends Button implements Serializable{
    @SerializedName("sub_button")
    private Button[] buttons;

    public Button[] getButtons() {
        return buttons;
    }

    public void setButtons(Button[] buttons) {
        this.buttons = buttons;
    }
}
