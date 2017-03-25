package com.github.congyh.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * HTTP工具类
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class HttpUtils {
    /**
     * 获取HTTP消息header信息
     *
     * @param req HTTP请求
     * @return header的key, value组成的map
     */
    public static <T extends ServletRequest> Map<String, String> getRequestHeaderMap(T req) {
        HttpServletRequest request = (HttpServletRequest) req;
        Map<String, String> map = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 获取HTTP Header的字符串表示形式
     *
     * @param req HTTP请求
     * @return HTTP Header的字符串表示形式
     */
    public static <T extends ServletRequest> String getRequestHeaderAsString(T req) {
        return getRequestHeaderMap(req).toString();
    }
}
