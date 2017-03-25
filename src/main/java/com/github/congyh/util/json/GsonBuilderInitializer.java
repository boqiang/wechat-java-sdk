package com.github.congyh.util.json;

import com.github.congyh.model.WeChatAccessToken;
import com.github.congyh.model.menu.ButtonWithSubbuttons;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * 初始化GsonBuilder对象
 *
 * <p>包含配套的GsonBuilder自定义序列化, 反序列化的配置代码
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@Deprecated
public class GsonBuilderInitializer {
    public static final GsonBuilder builder = new GsonBuilder();

    static {
        // 这里注册各种类型适配器.
        builder.registerTypeAdapter(WeChatAccessToken.class,
            new WeChatAccessTokenDeserializer());
    }

    /**
     * WeChatAccessToken反序列化自定义类
     */
    private static class WeChatAccessTokenDeserializer
        implements JsonDeserializer<WeChatAccessToken> {

        @Override
        public WeChatAccessToken deserialize(JsonElement jsonElement,
                                             Type type,
                                             JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
            WeChatAccessToken weChatAccessToken = new WeChatAccessToken();
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            /*以下字段只有在非空的情况下才会设置*/

            if (jsonObject.get("access_token") != null && !jsonObject.get("access_token").isJsonNull()) {
                weChatAccessToken.setAccessToken(jsonObject.get("access_token").getAsString());
            }
            if (jsonObject.get("expires_in") != null && !jsonObject.get("expires_in").isJsonNull()) {
                weChatAccessToken.setExpiresIn(jsonObject.get("expires_in").getAsInt());
            }
            return weChatAccessToken;
        }


        // 先通过样本代码来使用吧
        private void fromJsonSetFields(JsonElement jsonElement, Class<?> cls, String... jsonMembers)
            throws IllegalAccessException, InstantiationException {

            JsonObject jsonObject = jsonElement.getAsJsonObject();
            Object obj = cls.newInstance();
            for (String jsonMember : jsonMembers) {
                if (jsonObject.get(jsonMember) != null && !jsonObject.get(jsonMember).isJsonNull()) {
//                    Method SetMethod = cls.getMethod(
//                        "set" + CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, jsonMember), );
                }
            }
        }
    }
}
