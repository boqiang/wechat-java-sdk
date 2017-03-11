package com.github.congyh.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class VerifyUtils {

    /**
     * 校验请求(为了确认请求来自微信服务器)
     *
     * @param signature 微信加密签名(来自微信服务器)
     * @param timestamp 时间戳(来自微信服务器)
     * @param nonce 随机数(来自微信服务器)
     * @return true 校验成功 or false 校验失败
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] strs = new String[] {WeChatConst.TOKEN, timestamp, nonce};
        Arrays.sort(strs);
        String concatedStr = strs[0] + strs[1] + strs[2];
        // 获取字符串的十六进制加密表示形式
        String cipheredStr = DigestUtils.sha1Hex(concatedStr.getBytes());

        return cipheredStr.equals(signature);
    }
}
