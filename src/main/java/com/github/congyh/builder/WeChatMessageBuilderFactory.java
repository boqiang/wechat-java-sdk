package com.github.congyh.builder;

import com.github.congyh.builder.TextMessageBuilder;

/**
 * 工厂
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public final class WeChatMessageBuilderFactory {
    public static TextMessageBuilder buildText() {
        return new TextMessageBuilder();
    }
}
