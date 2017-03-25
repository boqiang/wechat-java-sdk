package com.github.congyh.model.menu;

import java.io.Serializable;

/**
 * 浏览类按钮
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class ViewButton extends Button implements Serializable {
    private static final long serialVersionUID = -7667556949897811621L;
    private String url;
    private String type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
