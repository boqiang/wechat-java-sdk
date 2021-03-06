package com.github.congyh.api.impl;

import com.github.congyh.api.WeChatConst;
import com.github.congyh.api.WeChatService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

/**
 * 微信公众平台后台中控Service
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class WeChatServiceImpl implements WeChatService {

    /**
     * 校验请求(为了确认请求来自微信服务器)
     *
     * @param req Http请求
     * @return true 校验成功 or false 校验失败
     */
    @Override
    public boolean checkSignature(HttpServletRequest req)
        throws ServletException, IOException {
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");

        String[] strs = new String[] {WeChatConst.TOKEN, timestamp, nonce};
        Arrays.sort(strs);
        String concatedStr = strs[0] + strs[1] + strs[2];
        // 获取字符串的十六进制加密表示形式
        String cipheredStr = DigestUtils.sha1Hex(concatedStr.getBytes());

        return cipheredStr.equals(signature);
    }
}
