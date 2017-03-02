package com.github.congyh.util.json;

import com.github.congyh.model.WeChatAccessToken;
import com.github.congyh.util.HttpsUtils;
import com.github.congyh.util.MyX509TrustManager;
import com.github.congyh.util.WeChatConst;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 测试通过Https get获取access_token, 并转化为WeChatAccessToken对象
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@Deprecated
public class HttpsTest {
    private static final Logger logger = Logger.getLogger(HttpsTest.class.getName());

    public static void main(String[] args)
        throws IOException, NoSuchProviderException,
        NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        String tokenUrl = WeChatConst.ACCESS_TOKEN_URL_PREFIX
            .concat("grant_type=client_credential&appid=")
            .concat(WeChatConst.APPID)
            .concat("&secret=")
            .concat(WeChatConst.SECRET);
        logger.log(Level.INFO, tokenUrl);
        URL url = new URL(tokenUrl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        TrustManager[] tm  = new TrustManager[] {new MyX509TrustManager()};
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, tm, new SecureRandom());
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        conn.setSSLSocketFactory(sslSocketFactory);
        conn.setDoInput(true);
        // 设置请求方式
        conn.setRequestMethod("GET");
        InputStream in = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(in, "utf-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuffer buffer = new StringBuffer();
        String str = null;
        while ((str = br.readLine()) != null) {
            buffer.append(str);
        }
        br.close();
        isr.close();
        in.close();
        conn.disconnect();
        logger.log(Level.INFO, GsonBuilderInitializer
            .builder.create().fromJson(buffer.toString(), WeChatAccessToken.class).toString());

        logger.log(Level.INFO, GsonBuilderInitializer
            .builder.create().fromJson(HttpsUtils.get(tokenUrl), WeChatAccessToken.class).toString());


    }
}
