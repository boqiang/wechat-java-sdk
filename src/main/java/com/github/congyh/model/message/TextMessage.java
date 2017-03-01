package com.github.congyh.model.message;

/**
 * 文本消息
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class TextMessage extends AbstractMessage {
    // 文本消息类型
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }
}
