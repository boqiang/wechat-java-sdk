package com.github.congyh.exception;

/**
 * 微信公众平台可能出现的各种异常
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class WeChatException extends RuntimeException {
    public WeChatException(String message) {
        super(message);
    }
}
