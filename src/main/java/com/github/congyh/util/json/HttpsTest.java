package com.github.congyh.util.json;

import com.github.congyh.model.WeChatAccessToken;
import com.github.congyh.model.menu.*;
import com.github.congyh.util.HttpsUtils;
import com.github.congyh.util.MyX509TrustManager;
import com.github.congyh.util.WeChatConst;
import org.apache.http.entity.StringEntity;

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

import static java.lang.System.out;

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
            + "grant_type=client_credential&appid="
            + WeChatConst.APPID
            + "&secret="
            + WeChatConst.SECRET;
        logger.log(Level.INFO, tokenUrl);
//        URL url = new URL(tokenUrl);
//        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//        TrustManager[] tm  = new TrustManager[] {new MyX509TrustManager()};
//        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
//        sslContext.init(null, tm, new SecureRandom());
//        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
//        conn.setSSLSocketFactory(sslSocketFactory);
//        conn.setDoInput(true);
//        // 设置请求方式
//        conn.setRequestMethod("GET");
//        InputStream in = conn.getInputStream();
//        InputStreamReader isr = new InputStreamReader(in, "utf-8");
//        BufferedReader br = new BufferedReader(isr);
//        StringBuffer buffer = new StringBuffer();
//        String str = null;
//        while ((str = br.readLine()) != null) {
//            buffer.append(str);
//        }
//        br.close();
//        isr.close();
//        in.close();
//        conn.disconnect();
//        logger.log(Level.INFO, GsonBuilderInitializer
//            .builder.create().fromJson(buffer.toString(), WeChatAccessToken.class).toString());

        WeChatAccessToken accessToken = GsonBuilderInitializer
            .builder.create().fromJson(HttpsUtils.get(tokenUrl), WeChatAccessToken.class);
        String postURL = WeChatConst.MENU_CREATE_URL_PREFIX + accessToken.getAccessToken();
        logger.info(postURL);

        ClickButton cbtn1 = new ClickButton();
        cbtn1.setName("今日问候");
        cbtn1.setType("click");
        cbtn1.setKey("V1001_GREETINGS");

        ViewButton vbtn1 = new ViewButton();
        vbtn1.setName("团队主页");
        vbtn1.setType("view");
        vbtn1.setUrl("http://congyh.com");

        ClickButton bws1 = new ClickButton();
        bws1.setName("加入我们");
        bws1.setType("click");
        bws1.setKey("V1001_JOIN_US");

        ClickButton bws2 = new ClickButton();
        bws2.setName("给我们点赞");
        bws2.setType("click");
        bws2.setKey("V1001_THUMB_UP");

        ButtonWithSubbuttons bws = new ButtonWithSubbuttons();
        bws.setName("更多");
        bws.setButtons(new Button[] {bws1, bws2});

        Menu menu = new Menu();
        menu.setButtons(new Button[] {cbtn1, vbtn1, bws});

        String jsonMenu = GsonBuilderInitializer.builder.create().toJson(menu);
        logger.info(jsonMenu);

        String returnJson = HttpsUtils.post(postURL, new StringEntity(jsonMenu));
        logger.info(returnJson);
    }
}
