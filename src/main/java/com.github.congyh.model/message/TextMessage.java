package com.github.congyh.model.message;

/**
 * 文本消息
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class TextMessage extends AbstractMessage {
    // 文本消息类型
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
