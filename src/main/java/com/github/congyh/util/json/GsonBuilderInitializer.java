package com.github.congyh.util.json;

import com.github.congyh.model.WeChatAccessToken;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * 初始化GsonBuilder对象
 *
 * <p>包含配套的GsonBuilder自定义序列化, 反序列化的配置代码
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class GsonBuilderInitializer {
    public static final GsonBuilder builder = new GsonBuilder();

    static {
        // 这里注册各种类型适配器.
        // TODO 目前仅对access_token的反序列化进行了自定义, 理论上可以添加无限个配置类.
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

        // TODO 通过jsonMembers设置实际的field值
        // 先通过样本代码来使用吧
        private void fromJsonSetFields(JsonElement jsonElement, Class<?> cls, String... jsonMembers)
            throws IllegalAccessException, InstantiationException {

            JsonObject jsonObject = jsonElement.getAsJsonObject();
            Object obj = cls.newInstance();
            for (String jsonMember : jsonMembers) {
                if (jsonObject.get(jsonMember) != null && !jsonObject.get(jsonMember).isJsonNull()) {
                    // TODO 这里还没有想好怎么根据jsonMember的实际类型来选择不同的函数调用
//                    Method SetMethod = cls.getMethod(
//                        "set" + CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, jsonMember), );
                }
            }
        }
    }
}
