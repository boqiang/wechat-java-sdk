package com.github.congyh.model.menu;

import java.io.Serializable;

/**
 * 点击类按钮
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class ClickButton extends Button implements Serializable {
    private static final long serialVersionUID = 3542199325191341436L;
    private String key;
    private String type;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
