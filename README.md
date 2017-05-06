# 微信公众平台后台

Javadoc: [详见这里][1]

使用说明:

1. maven添加依赖
   ```xml
   <dependency>
       <groupId>com.github.congyh</groupId>
       <artifactId>wechat-platform-core</artifactId>
       <version>0.3</version>
   </dependency>
   ```
2. 在resources文件夹中的wechat-config.properties文件, 填写以下配置内容:

    - token=这里是你在微信开发者接入界面填写的token
    - appid=可以在开发者界面上看到
    - secret=可以在开发者界面上看到
    - oauth2_redirect_uri=oauth2授权成功后的跳转页面(可选配置)
3. 开发自己的应用.



[1]: https://congyh.github.io/wechat-java-sdk