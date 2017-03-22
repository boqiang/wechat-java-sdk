package com.github.congyh.exception;

/**
 * 校验签名失败异常
 *
 * <p>非受检异常
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class CheckSignatureFailedException extends RuntimeException {

    public CheckSignatureFailedException(String message) {
        super(message);
    }

}