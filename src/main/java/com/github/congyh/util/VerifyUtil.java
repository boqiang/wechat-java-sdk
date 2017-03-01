package com.github.congyh.util;

import com.github.congyh.config.AppConfig;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class VerifyUtil {
    // 个人添加服务器配置的时候填写的字符
    private static final String token = AppConfig.getInstance().getProps().getProperty("token");

    /**
     * 校验请求(为了确认请求来自微信服务器)
     *
     * @param signature 微信加密签名(来自微信服务器)
     * @param timestamp 时间戳(来自微信服务器)
     * @param nonce 随机数(来自微信服务器)
     * @return true 校验成功 or false 校验失败
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        // out.println("token: " + VerifyUtil.token);
        String[] strs = new String[] {"asgsgsdgsdf", timestamp, nonce};
        Arrays.sort(strs);
        String concatedStr = strs[0].concat(strs[1]).concat(strs[2]);
        // 获取字符串的十六进制加密表示形式
        String cipheredStr = DigestUtils.sha1Hex(concatedStr.getBytes());

//        String cipheredStr = null;
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-1");
//            byte[] ciphered = md.digest(concatedStr.getBytes());
//            cipheredStr = new String(ciphered);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//        return cipheredStr != null && cipheredStr.equals(signature);
        return cipheredStr.equals(signature);
    }
}
