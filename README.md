# 微信公众平台后台

使用说明:

1. 在resources文件夹中的config.properties文件, 填写以下配置内容:

    - token=这里是你在微信开发者接入界面填写的token
    - appid=可以在开发者界面上看到
    - secret=可以在开发者界面上看到
2. mvn package, 并部署
3. 访问http://localhost:8080/CoreServlet即可.